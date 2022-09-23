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

package com.beneway.web.controller.system;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.beneway.basic.exception.RRException;
import com.beneway.basic.system.sys_files.po.SysFiles;
import com.beneway.basic.system.sys_files.service.SysFilesService;
import com.beneway.basic.utils.file.FileRuntimeException;
import com.beneway.basic.utils.file.FileUtil;
import com.beneway.basic.utils.office.WordUtils;
import com.beneway.basic.utils.sys_files.FilePathEnum;
import com.beneway.basic.utils.sys_files.FileTypeEnum;
import com.beneway.basic.utils.sys_files.SysFilesUtils;
import com.restful.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/sys_files")
public class SysFilesController {

    @Resource
    private SysFilesUtils sysFilesUtils;

    @Resource
    private SysFilesService sysFilesService;

    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file, String path){
        // 判断是否有目录
        if (StrUtil.isEmpty(path)){
            // 为空就设置默认路径
            path = sysFilesUtils.getCurrentFilesPath();
        }else{
            // 创建路径
            sysFilesUtils.createPath(path);
        }

        // 保存文件
        if (file != null) {
            //截取文件名
            String[] split = file.getOriginalFilename().split("\\.");
            //获取文件名以及后缀
            String filename = split[0];
            String suffix = "."+split[split.length - 1].toLowerCase();
            // 判断文件类型是否在白名单中
            if (FileTypeEnum.isNotExistFileType(suffix)){
                return Result.internalServerError("该文件类型不允许上传");
            }

            // 封装文件地址
            String fileId = IdUtil.simpleUUID();
            String filePath = sysFilesUtils.packStorPath(path, fileId);
            // 保存文件
            try {
                FileUtil.inputFile(file.getInputStream(), filePath);
            } catch (IOException e) {
                throw new FileRuntimeException(e);
            }

            // 保存文件信息到数据库中
            sysFilesService.saveFile(fileId, path, filename, suffix);

            return Result.success(fileId);
        }else{
            return Result.internalServerError("文件为空上传失败");
        }
    }

    /**
     * 下载文件
     * @param fileId
     * @param response
     * @return
     */
    @GetMapping("/downloadFile/{fileId}")
    public Result downloadFile(@PathVariable("fileId") String fileId, HttpServletResponse response){
        SysFiles sysFiles = sysFilesService.getById(fileId);
        if (sysFiles == null){
            return Result.internalServerError("文件不存在");
        }

        File file = sysFilesService.getFile(sysFiles);
        String filename = sysFiles.getFilename();
        String suffix = sysFiles.getSuffix();

        //设置相关参数
        response.setHeader("Accept-Ranges","bytes");
        response.setContentLengthLong(file.length());
        response.setHeader("Content-Disposition","attachment;filename=\""+
                new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)
                +suffix+ "\"");

        // 传输文件
        try {
            FileUtil.flow(new FileInputStream(file), response.getOutputStream());
        } catch (IOException e) {
            throw new FileRuntimeException(e);
        }

        return Result.success();
    }

    /**
     * 预览文件
     * @param fileId
     * @param response
     * @return
     */
    @GetMapping(value = "/previewFile/{fileId}")
    public void previewFile(@PathVariable("fileId") String fileId, HttpServletResponse response){
        SysFiles sysFiles = sysFilesService.getById(fileId);
        if (sysFiles == null){
            throw new RRException("文件不存在");
        }

        File file = sysFilesService.getFile(sysFiles);
        String filename = sysFiles.getFilename();
        String suffix = sysFiles.getSuffix();

        String pdfFilePath = null;

        // 根据不同的文件类型设置响应头
        FileTypeEnum fileTypeEnum = FileTypeEnum.getTypeBySuffix(suffix);
        if (FileTypeEnum.PDF.equals(fileTypeEnum)) {
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        } else if (FileTypeEnum.WORD.equals(fileTypeEnum)) {
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            // 将文件转为pdf
            pdfFilePath = sysFilesUtils.packStorPath(FilePathEnum.INTERIM, IdUtil.simpleUUID());
            try {
                WordUtils.docToPdf(file.getAbsolutePath(), pdfFilePath, suffix);
                file = new File(pdfFilePath);
            } catch (Exception e) {
                throw new FileRuntimeException("pdf转换失败", e);
            }
        } else if (FileTypeEnum.IMAGE.equals(fileTypeEnum)) {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        }
        // 传输文件
        try {
            FileUtil.flow(new FileInputStream(file), response.getOutputStream());

            // 删除临时pdf文件
            if (StrUtil.isNotEmpty(pdfFilePath)) {
                file.delete();
            }
        } catch (IOException e) {
            throw new FileRuntimeException(e);
        }
    }

    /**
     * 文件删除
     * @param fileId 文件id
     * @param isDel  是否真正删除文件
     * @return
     */
    @PutMapping("/delFile")
    public Result delFile(@RequestParam("fileId") String fileId,
                          @RequestParam(value = "isDel", defaultValue = "true") boolean isDel){
        if (isDel){
            sysFilesService.delFile(fileId);
        }
        return Result.success();
    }

    /**
     * 根据文件id串获取文件信息列表
     * @param ids
     * @return
     */
    @GetMapping("/getListByIds")
    public Result getListByIds(@RequestParam("ids") String ids){
        List<SysFiles> sysFilesList = sysFilesService.getListByIds(ids);
        return Result.success(sysFilesList);
    }

}
