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

package com.beneway.basic.utils.file;

import java.io.*;

public class FileUtil {

    /**
     * 输入文件到inputStream流中
     * @param inputStream
     * @param filePath
     */
    public static void inputFile(InputStream inputStream, String filePath){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            flow(inputStream, fileOutputStream);
        } catch (FileNotFoundException e) {
            throw new FileRuntimeException(e);
        }
    }

    /**
     * 输出文件到outputStream流中
     * @param outputStream
     * @param filePath
     */
    public static void outputFile(OutputStream outputStream, String filePath){
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            flow(fileInputStream, outputStream);
        } catch (FileNotFoundException e) {
            throw new FileRuntimeException(e);
        }
    }

    /**
     * copy文件
     * @param sourceFilePath
     * @param targetFilePath
     */
    public static void copyFile(String sourceFilePath, String targetFilePath){
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath);
            flow(fileInputStream, fileOutputStream);
        } catch (FileNotFoundException e) {
            throw new FileRuntimeException(e);
        }
    }

    public static void flow(InputStream inputStream, OutputStream outputStream){
        int count;
        byte[] buffer = new byte[4096];
        try {
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
        } catch (IOException e) {
            throw new FileRuntimeException(e);
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
