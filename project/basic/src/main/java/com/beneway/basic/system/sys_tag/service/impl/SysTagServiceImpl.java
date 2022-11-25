package com.beneway.basic.system.sys_tag.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_tag.dao.SysTagDao;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import com.beneway.basic.system.sys_tag.fo.SysTagFo;
import com.beneway.basic.system.sys_tag.fo.SysTagQueryFo;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import com.beneway.basic.system.sys_tag.vo.SysTagVo;
import com.beneway.basic.utils.page.PageUtils;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SysTagService")
public class SysTagServiceImpl extends ServiceImpl<SysTagDao, SysTag> implements SysTagService {


    @Resource
    private SysTagDao sysTagDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(SysTagFo sysTagFo) {
        this.save(sysTagFo);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result edit(SysTagFo sysTagFo) {
        this.updateById(sysTagFo);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result del(Integer tagId) {
        this.removeById(tagId);
        return Result.success();
    }

    @Override
    public Page<SysTagVo> queryPage(SysTagQueryFo sysTagQueryFo) {
        Page page = PageUtils.getPage(sysTagQueryFo);
        Page<SysTagVo> voPage = sysTagDao.queryPage(page, sysTagQueryFo);
        return voPage;
    }

    @Override
    public List<SysTag> getListByType(SysTagTypeEnum type) {
        List<SysTag> list = this.list(new LambdaQueryWrapper<SysTag>()
                .eq(SysTag::getTagType, type));
        return list;
    }
}
