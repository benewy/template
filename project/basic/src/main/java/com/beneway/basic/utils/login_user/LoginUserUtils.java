package com.beneway.basic.utils.login_user;

import cn.hutool.core.collection.CollUtil;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_unit.enums.SysUnitTypeEnum;
import com.beneway.basic.system.sys_user.entity.vo.LoginUserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2020/3/19
 * @time: 10:39
 */
public class LoginUserUtils {

    private static final String KEY = "loginUserInfo_";

    private static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 设置登陆用户信息
     * @param loginUserInfo
     */
    public static void setLoginUserInfo(LoginUserInfo loginUserInfo){
        if (loginUserInfo != null){
            HttpServletRequest request = getRequest();
            request.setAttribute(KEY, loginUserInfo);
        }
    }

    /**
     * 获取登录用户信息
     */
    public static LoginUserInfo getLoginUserInfo(){
        HttpServletRequest request = getRequest();
        LoginUserInfo loginUserInfo = (LoginUserInfo) request.getAttribute(KEY);
        return loginUserInfo;
    }

    /**
     * 获取登录人员id
     * @return
     */
    public static String getLoginUserId(){
        LoginUserInfo loginUserInfo = getLoginUserInfo();
        return loginUserInfo.getId();
    }

    /**
     * 是否是admin账户
     * @return
     */
    public static boolean isAdmin(){
        String loginUserId = getLoginUserId();
        return "1".equals(loginUserId);
    }

    /**
     * 根据类型获取当前单位id
     * @param typeEnum
     * @return
     */
    public static SysUnit getCurrentUnitOfType(SysUnitTypeEnum typeEnum) {
        LoginUserInfo loginUserInfo = getLoginUserInfo();
        List<SysUnit> unitList = loginUserInfo.getCurrentUnitInList();
        List<SysUnit> collect = unitList.stream()
                .filter(sysUnit -> sysUnit.getType().equals(typeEnum))
                .collect(Collectors.toList());

        SysUnit sysUnit = CollUtil.getLast(collect);

        return sysUnit;
    }

    /**
     * 根据类型获取当前单位id
     * @param typeEnum
     * @return
     */
    public static Integer getCurrentUnitIdOfType(SysUnitTypeEnum typeEnum) {
        SysUnit sysUnit = getCurrentUnitOfType(typeEnum);
        return sysUnit.getId();
    }

    /**
     * 获取当前单位id
     * @return
     */
    public static Integer getCurrentAgencyId() {
        return getCurrentUnitIdOfType(SysUnitTypeEnum.UNIT);
    }

    /**
     * 获取当前区域id
     * @return
     */
    public static Integer getCurrentAreaId() {
        return getCurrentUnitIdOfType(SysUnitTypeEnum.AREA);
    }



}
