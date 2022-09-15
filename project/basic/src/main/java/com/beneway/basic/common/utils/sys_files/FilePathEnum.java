package com.beneway.basic.common.utils.sys_files;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/3/23 13:01
 */
public enum FilePathEnum {

    /**
     * 文件存放地址
     */
    FILES("files", true),
    /**
     * 模板文档文件夹
     */
    TEMPLATE("template", true),
    /**
     * 临时文件存放地址
     */
    INTERIM("interim", true);

    /**
     * 路径
     */
    private final String path;

    /**
     * 是否为文件夹
     */
    private final boolean isFolder;

    FilePathEnum(String path, boolean isFolder) {
        this.path = path;
        this.isFolder = isFolder;
    }

    public String getPath() {
        return path;
    }

    public boolean isFolder() {
        return isFolder;
    }
}
