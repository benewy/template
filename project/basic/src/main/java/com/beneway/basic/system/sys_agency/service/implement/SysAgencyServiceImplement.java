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

package com.beneway.basic.system.sys_agency.service.implement;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_agency.dao.SysAgencyDao;
import com.beneway.basic.system.sys_agency.fo.SysAgencyQueryFo;
import com.beneway.basic.system.sys_agency.po.SysAgency;
import com.beneway.basic.system.sys_agency.service.SysAgencyService;
import com.beneway.basic.system.sys_agency.vo.SysAgencyVo;
import com.beneway.basic.utils.ClassUtil;
import com.beneway.basic.utils.page.PageUtils;
import com.restful.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 15:30:50
 */
@Service("sysAgencyService")
public class SysAgencyServiceImplement extends ServiceImpl<SysAgencyDao, SysAgency> implements SysAgencyService {

    @Resource
    private SysAgencyDao sysAgencyDao;

    @Override
    public Result queryPage(SysAgencyQueryFo sysAgencyQueryFo) {
        Page page = PageUtils.getPage(sysAgencyQueryFo);
        Page<SysAgencyVo> voPage = sysAgencyDao.queryPage(page, sysAgencyQueryFo);
        return Result.success(voPage);
    }

    @Override
    public Result insert(SysAgency sysAgency) {
        return Result.success(save(sysAgency));
    }

    @Override
    public List<SysAgencyVo> getTreeListByPid(Integer pid) {
        return getTreeListByPid(list(), pid);
    }

    /**
     * 通过用户的ID获取该用户所属的部门信息
     * @param userID
     * @return
     */
    @Override
    public List<SysAgency> getByUser(String userID) {
        LambdaQueryWrapper<SysAgency> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(SysAgency::getUserIds,userID);
        return this.list(lambdaQueryWrapper);
    }

    /**
     * 查找父机构（isFather = 1）
     * @param id
     * @return
     */
    @Override
    public SysAgency getFather(String id) {
        //通过id查找到单个机构
        SysAgency sysAgency = getById(id);
        //判断机构是否为父机构
        while ( ! "1".equals(sysAgency.getIsFather())){
            sysAgency = getById(sysAgency.getPid());
        }
        return sysAgency;
    }
    @Override
    public String getAgency(String agencyId) {
        //通过id查找到单个机构
        SysAgency sysAgency = getById(agencyId);
        String agencyName = "";
        //判断机构是否为父机构
        if (sysAgency.getIsFather().equals("1")){
            //机构为父机构
            agencyName = agencyName + sysAgency.getName();
        }else {
            //机构非父机构
            SysAgency sysAgencyFather = getById(sysAgency.getPid());
            agencyName = agencyName + sysAgencyFather.getName() + "/" + sysAgency.getName();
        }
        return agencyName;
    }

    private List<SysAgencyVo> getTreeListByPid(List<SysAgency> list, Integer pid) {
        List<SysAgencyVo> sysAgencyVoList = ClassUtil.toClassList(list,SysAgencyVo.class);
        List<SysAgencyVo> fList = sysAgencyVoList.stream().filter(sysUnitVo -> pid.equals(sysUnitVo.getPid())).collect(Collectors.toList());
        children(fList, sysAgencyVoList);
        return fList;
    }

    private void children(List<SysAgencyVo> fList, List<SysAgencyVo> list){
        for (SysAgencyVo sysUnitVo : fList) {
            Integer id = sysUnitVo.getId();
            List<SysAgencyVo> children = list.stream().filter(sysUnit -> sysUnit.getPid().equals(id)).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(children)){
                sysUnitVo.setChildren(children);
                children(children, list);
            }
        }
    }
}
