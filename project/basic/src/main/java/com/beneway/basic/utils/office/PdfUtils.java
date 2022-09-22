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
