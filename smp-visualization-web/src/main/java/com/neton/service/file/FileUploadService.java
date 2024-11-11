package com.neton.service.file;

import com.neton.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public CommonResult<String> uploadFile(MultipartFile file);
}
