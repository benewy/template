/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

package com.beneway.core.service.sys_user.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.basic.exception.RRException;
import com.beneway.basic.exception.ResultException;
import com.beneway.basic.log.log_login.enums.LoginTypeEnum;
import com.beneway.basic.log.log_login.service.LogLoginService;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.RedisUtils;
import com.beneway.basic.utils.dd.ZWDDPhoneUtils;
import com.beneway.basic.utils.dd.ZWDDQrcodeUtils;
import com.beneway.core.entity.sys_user.fo.LoginFo;
import com.beneway.core.entity.sys_user.fo.LoginVo;
import com.beneway.core.service.sys_user.AdminSysUserService;
import com.beneway.web.service.LoginService;
import com.google.gson.JsonObject;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("adminSysUserService")
public class AdminSysUserServiceImpl implements AdminSysUserService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private LoginService loginService;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ZWDDQrcodeUtils zwddQrcodeUtils;

    @Resource
    private ZWDDPhoneUtils zwddPhoneUtils;

    @Resource
    private LogLoginService logLoginService;

    @Override
    public Result login(LoginFo loginFo) {
        String account = loginFo.getAccount();
        String verifyCode = loginFo.getVerifyCode();

        // 判断验证码是否正确
        String code = loginService.getLoginVerifyCode();
        if (StrUtil.isNotEmpty(verifyCode)) {
            if (!verifyCode.equalsIgnoreCase(code)) {
                return Result.internalServerError("验证码错误");
            }
        } else {
            return Result.internalServerError("验证码不能为空");
        }

        // 记录账号密码错误次数一天不能多于10次
        long loginNumOutTime = DateUnit.HOUR.getMillis() * 24;
        String loginNumRedisKey = "loginNum:" + account;
        String loginNumStr = redisUtils.get(loginNumRedisKey, loginNumOutTime);
        if (StrUtil.isNotEmpty(loginNumStr)) {
            int loginNum = Integer.parseInt(loginNumStr);
            if (loginNum > 10) {
                throw new ResultException(HttpStatus.LOCKED,"密码错误超过10次账号已被冻结24小时，联系管理员进行解冻");
            }
        }

        String password = loginFo.getPassword();
        password = sysUserService.passEncr(password);
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getAccount, account)
                .eq(SysUser::getPassword, password));
        if (sysUser != null) {
            // 封装登录成功后的用户信息
            StpUtil.login(sysUser.getId());
            LoginVo loginVo = new LoginVo(sysUser);
            loginVo.setToken(StpUtil.getTokenValue());

            // 保存日志记录
            logLoginService.addLoginLog(sysUser.getId(), LoginTypeEnum.FORM);

            return Result.success(loginVo);
        } else {
            int loginNum = 0;
            if (StrUtil.isNotEmpty(loginNumStr)) {
                loginNum = Integer.parseInt(loginNumStr);
            }
            loginNum += 1;
            redisUtils.set(loginNumRedisKey, loginNum, loginNumOutTime);

            return Result.internalServerError("账号或密码错误");
        }

    }

    /**
     * pc扫码登陆
     *
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result qrLogin(String code) {
        JsonObject data = zwddQrcodeUtils.getUserInfoQrcode(code);
        if (null != data) {
            String employeeCode = data.get("employeeCode").getAsString();
            LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysUser::getEmployeeCode, employeeCode);
            SysUser sysUser = sysUserService.getOne(wrapper);
            if (null != sysUser) {
                // 封装登录成功后的用户信息
                StpUtil.login(sysUser.getId());
                LoginVo loginVo = new LoginVo(sysUser);
                loginVo.setToken(StpUtil.getTokenValue());

                // 保存日志记录
                logLoginService.addLoginLog(sysUser.getId(), LoginTypeEnum.QRCODE);

                return Result.success(loginVo);
            } else {
                return Result.internalServerError("用户不存在");
            }
        } else {
            return Result.internalServerError("权限不足，扫码登录失败");
        }
    }
}
