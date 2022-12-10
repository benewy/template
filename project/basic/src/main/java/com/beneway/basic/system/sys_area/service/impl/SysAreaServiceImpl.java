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

package com.beneway.basic.system.sys_area.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_area.dao.SysAreaDao;
import com.beneway.basic.system.sys_area.po.SysArea;
import com.beneway.basic.system.sys_area.service.SysAreaService;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行政区域表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-18 16:24:03
 */
@EnableAspectJAutoProxy( proxyTargetClass = true , exposeProxy = true )
@CacheConfig(cacheNames = "sysArea")
@Service("sysAreaService")
public class SysAreaServiceImpl extends ServiceImpl<SysAreaDao, SysArea> implements SysAreaService {

    @Resource
    private SysAreaDao sysAreaDao;

    private SysAreaService getCurrThis(){
      return (SysAreaService) AopContext.currentProxy();
    }

    @Cacheable(key = "'list'")
    @Override
    public List<SysArea> list() {
        return super.list(new LambdaQueryWrapper<SysArea>().orderByAsc(SysArea::getId));
    }

}
