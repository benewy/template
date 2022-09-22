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

package com.beneway.basic.system.sys_files.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_files.dao.SysFilesDao;
import com.beneway.basic.system.sys_files.po.SysFiles;
import com.beneway.basic.system.sys_files.service.SysFilesService;
import com.beneway.basic.utils.sys_files.FilePathEnum;
import com.beneway.basic.utils.sys_files.SysFilesUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("sysFilesService")
public class SysFilesServiceImpl extends ServiceImpl<SysFilesDao, SysFiles> implements SysFilesService {

    @Resource
    private SysFilesUtils sysFilesUtils;

    @Resource
    private SysFilesDao sysFilesDao;

    @Override
    public void saveFile(String fileId, String path, String filename, String suffix) {
        SysFiles sysFiles = new SysFiles();
        sysFiles.setId(fileId);
        sysFiles.setPath(path);
        sysFiles.setFilename(filename);
        sysFiles.setSuffix(suffix);
        sysFiles.setCreateTime(new Date());
        this.save(sysFiles);
    }

    @Override
    public void saveFile(String fileId, FilePathEnum filePathEnum, String filename, String suffix) {
        saveFile(fileId, filePathEnum.getPath(), filename, suffix);
    }

    @Override
    public void saveInterimFile(String fileId, String filename, String suffix) {
        this.saveFile(fileId, FilePathEnum.INTERIM, filename, suffix);
    }

    @Override
    public void delFile(String fileId) {
        File file = getFile(fileId);
        if (file.exists()) {
            file.delete();
        }
        this.removeById(fileId);
    }

    @Override
    public File getFile(String fileId) {
        SysFiles sysFiles = this.getById(fileId);
        return getFile(sysFiles);
    }

    @Override
    public File getFile(SysFiles sysFiles) {
        String path = sysFiles.getPath();
        String id = sysFiles.getId();
        String realPath = sysFilesUtils.packStorPath(path, id);
        File file = new File(realPath);
        return file;
    }

    @Override
    public List<SysFiles> getListByIdList(List<String> idList){
        if (CollUtil.isNotEmpty(idList)){
            List<SysFiles> sysFilesList = this.list(new LambdaQueryWrapper<SysFiles>()
                    .in(SysFiles::getId, idList)
                    .orderByAsc(SysFiles::getCreateTime));
            return sysFilesList;
        }else{
            return new ArrayList<>();
        }
    }


    @Override
    public List<SysFiles> getListByIds(String ids){
        if (StrUtil.isNotEmpty(ids)){
            if(ids.endsWith(",")){
                ids = ids.substring(0, ids.length() - 1);
            }
            List<String> idList = new ArrayList<>(Arrays.asList(ids.split(",")));
            return getListByIdList(idList);
        }else{
            return new ArrayList<>();
        }
    }

    /**
     * 获取文件真实地址
     * @param filesId
     * @return
     */
    @Override
    public String getFileRealAddress(String filesId) {
        SysFiles sysFiles = this.getById(filesId);
        return getFileRealAddress(sysFiles);
    }

    @Override
    public String getFileRealAddress(SysFiles sysFiles) {
        return sysFilesUtils.packStorPath(sysFiles.getPath(), sysFiles.getId());
    }

}
