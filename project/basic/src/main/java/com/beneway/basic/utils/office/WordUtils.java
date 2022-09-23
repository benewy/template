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

package com.beneway.basic.utils.office;

import cn.hutool.core.util.StrUtil;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2020/7/17
 * @time: 10:34
 */
public class WordUtils {

    public static void creatWordByModel(String tmpFile, Map<String, String> contentMap, String exportFile){

        // 获取模板文件
        Document document = new Document(tmpFile);

        // 根据map替换文本信息
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StrUtil.isEmpty(value)){
                String s = "";
                for (int i = 0; i < key.length() + 3; i++) {
                    s += " ";
                }
                value = s;
            }
//            document.replace("${" + key + "}", value, false, true);
            document.replace(Pattern.compile("\\$\\{" + key + "\\}"), value);
        }

        // 保存文本
        document.saveToFile(exportFile, FileFormat.Docx_2013);

    }

    /**
     *
     * @param docFilePath doc文件地址
     * @param pdfFilePath pdf文件地址
     * @param suffix      word格式后缀
     * @throws Exception
     */
    public static void docToPdf(String docFilePath, String pdfFilePath, String suffix) throws Exception {
        //加载word示例文档
        Document document = new Document();
        document.loadFromFile(docFilePath);
        int pageCount = document.getPageCount();
        if (pageCount > 3){
            OfficeToPdf.word2Pdf(docFilePath, pdfFilePath);
        }else{
            //保存结果文件
            document.saveToFile(pdfFilePath, FileFormat.PDF);
        }
    }

}
