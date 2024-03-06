package com.zzf.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzf
 * @date 2024-01-07
 */
public interface FileUploadService {
    String fileUpload(MultipartFile multipartFile);
}
