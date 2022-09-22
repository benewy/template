package com.beneway.basic.system.sys_tag.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_tag.dao.SysTagMapDao;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.po.SysTagMap;
import com.beneway.basic.system.sys_tag.service.SysTagMapService;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 *
 * @author wkx
 * @email 1181597045@qq.com
 * @date 2022-03-04 14:04:38
 */
@Service("sysTagMapService")
public class SysTagMapServiceImpl extends ServiceImpl<SysTagMapDao, SysTagMap> implements SysTagMapService {

    @Resource
    private SysTagMapDao sysTagMapDao;

    @Resource
    private SysTagService sysTagService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTagMap(String assId, List<Integer> tagList, SysTagTypeEnum tagTypeEnum) {
        List<SysTagMap> list = new LinkedList<>();
        for (Integer tagId : tagList) {
            SysTagMap sysTagMap = new SysTagMap();
            sysTagMap.setSysTagId(tagId);
            sysTagMap.setAssId(assId);
            sysTagMap.setType(tagTypeEnum);
            list.add(sysTagMap);
        }
        this.saveBatch(list);
    }

    @Override
    public void addTagMap(String assId, Integer tagId, SysTagTypeEnum tagTypeEnum) {
        SysTagMap sysTagMap = new SysTagMap();
        sysTagMap.setSysTagId(tagId);
        sysTagMap.setAssId(assId);
        sysTagMap.setType(tagTypeEnum);
        this.save(sysTagMap);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTagMap(String assId, List<Integer> tagList, SysTagTypeEnum tagTypeEnum) {
        removeTagMap(assId,tagTypeEnum);
        addTagMap(assId, tagList, tagTypeEnum);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeTagMap(String assId, SysTagTypeEnum tagTypeEnum) {
        this.remove(new LambdaQueryWrapper<SysTagMap>()
                .eq(SysTagMap::getAssId, assId)
                .eq(SysTagMap::getType, tagTypeEnum));
    }

    @Override
    public List<SysTag> getListByAssId(String assId, SysTagTypeEnum sysTagTypeEnum) {
        List<Integer> tagIdList = getIdListByAssId(assId, sysTagTypeEnum);
      return sysTagService.listByIds(tagIdList);
    }

    @Override
    public List<Integer> getIdListByAssId(String assId, SysTagTypeEnum typeEnum) {
        List<SysTagMap> list = this.list(new LambdaQueryWrapper<SysTagMap>()
                .eq(SysTagMap::getAssId, assId)
                .eq(SysTagMap::getType, typeEnum));

        List<Integer> tagIdList = list.stream().map(SysTagMap::getSysTagId).collect(Collectors.toList());

        return tagIdList;
    }

    @Override
    public List<Integer> getIdListByAssIdList(List<String> assIdList, SysTagTypeEnum typeEnum) {
        List<SysTagMap> list = this.list(new LambdaQueryWrapper<SysTagMap>()
                .in(SysTagMap::getAssId, assIdList)
                .eq(SysTagMap::getType, typeEnum));

        List<Integer> tagIdList = list.stream().map(SysTagMap::getSysTagId).collect(Collectors.toList());

        return tagIdList;
    }

    @Override
    public Set<String> getAssIdSetByTagIdList(List<Integer> idList) {
        List<SysTagMap> list = this.list(new LambdaQueryWrapper<SysTagMap>()
                .select(SysTagMap::getAssId)
                .in(SysTagMap::getSysTagId, idList));
        return list.stream().map(SysTagMap::getAssId).collect(Collectors.toSet());
    }

}
