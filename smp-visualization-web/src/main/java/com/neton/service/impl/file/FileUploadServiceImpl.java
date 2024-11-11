package com.neton.service.impl.file;

import com.neton.common.CommonResult;
import com.neton.minio.config.MinioConfig;
import com.neton.service.file.FileUploadService;
import com.neton.utils.DateUtils;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioConfig minioConfig;
    @Override
    public CommonResult<String> uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String string = DateUtils.formatDateToString(DateUtils.getSystemDate(), "yyyy-MM-dd");
        String newFileName = string+"/"+ UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(fileName, ".");
        String contentType = file.getContentType();
        uploadFile(minioConfig.getBucketName(),file,newFileName,contentType);
        // 返回文件的访问地址
        String fileUrl = minioConfig.getEndpoint() +"/"+minioConfig.getBucketName() +"/"+ newFileName;
        return CommonResult.success(fileUrl);
    }

    @SneakyThrows(Exception.class)
    public ObjectWriteResponse uploadFile(String bucketName, MultipartFile file, String objectName, String contentType) {
        InputStream inputStream = file.getInputStream();
        return minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .contentType(contentType)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
    }
}
