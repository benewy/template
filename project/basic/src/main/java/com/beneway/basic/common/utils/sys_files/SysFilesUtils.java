package com.beneway.basic.common.utils.sys_files;

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
    private String filePath;

    private String files = FilePathEnum.FILES.getPath();

    /**
     * 临时文件存放地址
     */
    private String interim = FilePathEnum.INTERIM.getPath();


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
                .filter(filePathEnum -> filePathEnum.isFolder())
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
