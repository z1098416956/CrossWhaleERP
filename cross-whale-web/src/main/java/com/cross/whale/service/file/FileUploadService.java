package com.cross.whale.service.file;

import com.cross.whale.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public CommonResult<String> uploadFile(MultipartFile file);
}
