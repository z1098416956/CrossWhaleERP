package com.neton.controller.file;

import com.neton.common.CommonResult;
import com.neton.service.file.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/file/uploadFile")
    public CommonResult<String> uploadFile(@RequestParam("file")MultipartFile file){

        return fileUploadService.uploadFile(file);
    }
}
