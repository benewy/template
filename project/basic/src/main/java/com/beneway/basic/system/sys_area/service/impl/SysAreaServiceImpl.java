package com.beneway.basic.system.sys_area.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_area.dao.SysAreaDao;
import com.beneway.basic.system.sys_area.po.SysArea;
import com.beneway.basic.system.sys_area.service.SysAreaService;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
