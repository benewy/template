package com.beneway.basic.common.utils.file;

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
