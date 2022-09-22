package com.beneway.basic.system.sys_tag.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.mybatisplus.MyIService;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import com.beneway.basic.system.sys_tag.fo.SysTagFo;
import com.beneway.basic.system.sys_tag.fo.SysTagQueryFo;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.vo.SysTagVo;
import com.restful.Result;

import java.util.List;

public interface SysTagService extends MyIService<SysTag> {

    /**
     * 新增
     * @param sysTagFo
     * @return
     */
    Result add(SysTagFo sysTagFo);

    /**
     * x编辑
     * @param sysTagFo
     * @return
     */
    Result edit(SysTagFo sysTagFo);

    /**
     * 用户删除
     * @param tagId
     * @return
     */
    Result del(Integer tagId);

    /**
     * 分页查询
     * @param sysTagQueryFo
     * @return
     */
    Page<SysTagVo> queryPage(SysTagQueryFo sysTagQueryFo);

    /**
     * 根据类型获取标签列表
     * @param type
     * @return
     */
    List<SysTag> getListByType(SysTagTypeEnum type);
}
