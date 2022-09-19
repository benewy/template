/**
 * 版权所有 2022 - 至今 为 杭州融慧数据科技有限公司 所有
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

package com.beneway.basic.common.utils.sys_files;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by zhy on 2022/3/3 11:33
 *
 * 文件类型枚举
 */
public enum FileTypeEnum {

    /**
     * 图片
     */
    IMAGE("img", ".png", ".jpeg", ".jpg"),

    /**
     * word
     */
    WORD("word", ".doc", ".docx", ".wps"),

    /**
     * pdf
     */
    PDF("pdf", ".pdf");


    /**
     * 类型
     */
    private final String type;

    /**
     * 类型对应文件后缀
     */
    private final String[] suffixs;

    FileTypeEnum(String type, String... suffixs){
        this.type = type;
        this.suffixs = suffixs;
    }

    private static final List<String> fileTypes = getFileTypes();
    private static final List<String> getFileTypes() {
        FileTypeEnum[] typeEnums = values();
        List<String> stringList = Arrays.stream(typeEnums)
                .flatMap(fileTypeEnum -> Arrays.stream(fileTypeEnum.suffixs))
                .collect(Collectors.toList());
        return stringList;
    }

    public static boolean isExistFileType(String suffix) {
        return fileTypes.contains(suffix);
    }

    public static boolean isNotExistFileType(String suffix) {
        return !isExistFileType(suffix);
    }

    public static boolean isType(FileTypeEnum fileTypeEnum, String suffix){
        return Arrays.asList(fileTypeEnum.suffixs).contains(suffix);
    }

    public static boolean isImage(String suffix){
        return isType(IMAGE, suffix);
    }

    public static boolean isWord(String suffix){
        return isType(WORD, suffix);
    }

    public static boolean isPdf(String suffix){
        return isType(PDF, suffix);
    }

    public static FileTypeEnum getTypeBySuffix (String suffix) {
        FileTypeEnum[] enums = values();
        for (FileTypeEnum typeEnum : enums) {
            boolean b = Arrays.asList(typeEnum.suffixs).contains(suffix);
            if (b) {
                return typeEnum;
            }
        }
        return null;
    }

}
