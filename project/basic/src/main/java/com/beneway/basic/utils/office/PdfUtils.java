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

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/3/23 9:46
 */
public class PdfUtils {

    public static void main(String[] args) {
        String pdfPath = "/Users/lichen/Desktop/应急处突小程序上架材料清单/2.压测报告/报告.pdf";
        String outputFileName = "/Users/lichen/Downloads/1.pdf";
        String imagePath = "/Users/lichen/Downloads/水印.png";

        try {
            addWaterMaker(pdfPath, imagePath, outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加水印
     *
     * @param pdfPath
     * @param imagePath
     * @param outPdfPath
     */
    public static void addWaterMaker(String pdfPath, String imagePath, String outPdfPath) throws Exception {
        // 获取pdf大小
        PdfReader reader = new PdfReader(pdfPath);
        Rectangle pageSize = reader.getPageSize(1);
        float width = pageSize.getWidth();
        float height = pageSize.getHeight();

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outPdfPath));
        int pageCount = reader.getNumberOfPages();
        stamper.getUnderContent(0);

        // 水印图片
        Image image = Image.getInstance(imagePath);
        image.setAbsolutePosition(0, 0);
        // 透明度
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.4f);
        image.scaleAbsolute(width, height);

        for (int i = 1; i <= pageCount; i++) {
            PdfContentByte pdfContentByte = stamper.getOverContent(i);
            pdfContentByte.setGState(gs1);
            pdfContentByte.addImage(image);
        }

        // todo: ---
        stamper.close();
        reader.close();
    }

}
