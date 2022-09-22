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

package com.beneway.basic.system.sys_files.service;

import com.beneway.basic.mybatisplus.MyIService;
import com.beneway.basic.system.sys_files.po.SysFiles;
import com.beneway.basic.utils.sys_files.FilePathEnum;

import java.io.File;
import java.util.List;

public interface SysFilesService extends MyIService<SysFiles> {

    /**
     * 保存文件
     * @param fileId
     * @param path
     * @param filename
     * @param suffix
     */
    void saveFile(String fileId, String path, String filename, String suffix);

    void saveFile(String fileId, FilePathEnum filePathEnum, String filename, String suffix);

    /**
     * 保存临时文件
     * @param fileId
     * @param filename
     * @param suffix
     */
    void saveInterimFile(String fileId, String filename, String suffix);

    /**
     * 删除文件
     * @param fileId
     */
    void delFile(String fileId);

    /**
     * 获取文件file对象
     * @param fileId
     * @return
     */
    File getFile(String fileId);

    File getFile(SysFiles sysFiles);

    /**
     * 获取文件信息列表
     * @param idList
     * @return
     */
    List<SysFiles> getListByIdList(List<String> idList);

    List<SysFiles> getListByIds(String ids);

    /**
     * 获取文件的绝对地址
     * @param filesId
     * @return
     */
    String getFileRealAddress(String filesId);

    String getFileRealAddress(SysFiles files);

}
