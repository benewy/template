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

package com.beneway.basic.utils.sys_files;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2021/1/19
 * @time: 18:03
 */
@Data
@Component
@ConfigurationProperties(prefix = "customize.file")
public class SysFilesUtils {

  /**
   * 文件存放地址
   */
  private String filePath = FilePathEnum.FILES.getPath();

  private String files = FilePathEnum.FILES.getPath();

  /**
   * 临时文件存放地址
   */
  private String interim = FilePathEnum.INTERIM.getPath();

  /**
   * 模板文件存放地址
   */
  private String template = FilePathEnum.TEMPLATE.getPath();

  /**
   * 模板文件存放地址
   */
  private String watermark = FilePathEnum.WATERMARK.getPath();

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @PostConstruct
  public void init(){
    File file = createMkdirs(filePath);
    this.filePath = file.getAbsolutePath() + "/";
    // 初始化其它目录
    FilePathEnum[] filePathEnums = FilePathEnum.values();
    List<FilePathEnum> pathEnumList = Arrays.stream(filePathEnums)
      .filter(FilePathEnum::isFolder)
      .collect(Collectors.toList());
    for (FilePathEnum filePathEnum : pathEnumList) {
      createPath(filePathEnum.getPath());
    }
  }

  private File createMkdirs(String path){
    File file = new File(path);
    if (!file.exists()) {
      file.mkdirs();
    }
    return file;
  }

  /**
   * 创建文件目录
   * @param path
   */
  public void createPath(String path){
    createMkdirs(packStorPath(path));
  }

  /**
   * 获取路径
   * @param paths
   * @return
   */
  public String packStorPath(String... paths){
    String p = this.filePath;
    for (String s : paths) {
      if (StrUtil.isEmpty(s)){
        continue;
      }
      p += (s + "/");
    }
    p = p.replaceAll("//", "/");
    if (p.lastIndexOf("/") == p.length() - 1){
      p = p.substring(0, p.length() - 1);
    }
    return p;
  }

  public String packStorPath(FilePathEnum filePathEnum, String... paths) {
    String[] strings = new String[1 + paths.length];
    strings[0] = filePathEnum.getPath();
    for (int i = 1; i <= paths.length; i++) {
      strings[i] = paths[i - 1];
    }
    return packStorPath(strings);
  }

  /**
   * 获取当前files下的目录
   * @return
   */
  public String getCurrentFilesPath() {
    // 获取当天日期格式
    String date = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
    // 拼接当前files目录
    String path = FilePathEnum.FILES.getPath() + "/" + date;
    // 获取绝对路径
    String absolutePath = packStorPath(path);
    // 创建路径存在则不进行创建
    File file = new File(absolutePath);
    if (!file.exists()) {
      file.mkdirs();
    }
    // 返回路径
    return path;
  }

}
