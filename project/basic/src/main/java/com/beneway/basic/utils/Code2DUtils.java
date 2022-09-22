package com.beneway.basic.utils;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.beneway.basic.utils.sys_files.FilePathEnum;
import com.beneway.basic.utils.sys_files.SysFilesUtils;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class Code2DUtils {

    @Resource
    private SysFilesUtils sysFilesUtils;

    /**
     * height是合成后的图片高度
     * res 是背景图，logo，以及生成后的图片的保存地址
     *
     * @return
     * @author yhz
     * @create 2022/4/12 14:10
     * @Param
     **/
    public File imageGeneration(BufferedImage bufferedImage, String title, int height) {
        //背景图，logo，以及生成后的图片的保存地址
        File bg = new File(sysFilesUtils.packStorPath(FilePathEnum.CODE, "bg.png"));
        File logo = new File(sysFilesUtils.packStorPath(FilePathEnum.CODE, "logo2.png"));
        File img = new File(sysFilesUtils.packStorPath(FilePathEnum.CODE, "saved2.png"));
        try {
            ImageIO.write(bufferedImage, "png", img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = height * 8 / 10;
        int codeWidth = height * 2 / 3;
        int codeHeight = height * 2 / 3;
        int logoWidth = height / 8;
        int logoHeight = height / 8;
        int size = height / 20;
        //截取标题，过长省略
        if (title.length() > 13) {
            title = title.substring(0, 12) + "…";
        }
        //缩放背景
        ImgUtil.scale(
                FileUtil.file(bg),
                FileUtil.file(bg),
                width,
                height,
                Color.white
        );
        //缩放二维码
        ImgUtil.scale(
                FileUtil.file(img),
                FileUtil.file(img),
                codeWidth,
                codeHeight,
                null
        );
        //缩放logo
        ImgUtil.scale(
                FileUtil.file(logo),
                FileUtil.file(logo),
                logoWidth,
                logoHeight,
                null
        );
        //给二维码添加背景图
        ImgUtil.pressImage(
                FileUtil.file(bg),
                FileUtil.file(img),
                ImgUtil.read(FileUtil.file(img)), //二维码图片
                0,
                (height / 8),
                1.0f
        );
        //添加logo
        ImgUtil.pressImage(
                FileUtil.file(img),
                FileUtil.file(img),
                ImgUtil.read(FileUtil.file(logo)), //水印图片
                -(width * 5 / 14),
                -(height * 2 / 5),
                1.0f
        );
        ImgUtil.pressText(
                FileUtil.file(img),
                FileUtil.file(img),
                title, Color.black,
                new Font("宋体", Font.BOLD, size),
                0,
                -(height / 4),
                1.0f
        );
        ImgUtil.pressText(
                FileUtil.file(img),
                FileUtil.file(img),
                "扫码查看合同", Color.gray,
                new Font("宋体", Font.BOLD, size / 2),
                0,
                -(height * 4 / 10),
                1.0f
        );
        return img;
    }

    /**
     * 生成二维码
     *
     * @param content 内容
     */
    public BufferedImage createCode(String url, String content, int width, int height) {
        QrConfig config = new QrConfig(width, height);

        String path = sysFilesUtils.packStorPath(FilePathEnum.LOGO);
        File file = new File(path);
        //logo
        config.setImg(file);
        // 边距
        config.setMargin(0);
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.Q);
        // 前景色
        config.setForeColor(new Color(24, 144, 255).getRGB());
        // 背景色
//        config.setBackColor(new Color(255, 255, 255).getRGB());

        // 地址
        url += "?id=" + content;
        BufferedImage bufferedImage = QrCodeUtil.generate(//
                url, //二维码内容
                config
        );
        return bufferedImage;


    }

    /**
     * 识别二维码
     *
     * @param address 图片地址
     * @return
     */
    private static String decode(String address) {
        String decode = QrCodeUtil.decode(FileUtil.file(address));
        return decode;
    }

}
