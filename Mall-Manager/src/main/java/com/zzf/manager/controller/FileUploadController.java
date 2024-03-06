package com.zzf.manager.controller;

import com.zzf.manager.service.FileUploadService;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzf
 * @date 2024-01-07
 */
@Tag(name = "图片接口")
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Operation(summary = "上传图片接口")
    @PostMapping(value = "/fileUpload")
    public Result<String> fileUploadService(@RequestParam(value = "file") MultipartFile multipartFile) {
        //1.获取上传的文件
        String fileUrl = fileUploadService.fileUpload(multipartFile) ;
        return Result.build(fileUrl , ResultCodeEnum.SUCCESS) ;
    }
}
