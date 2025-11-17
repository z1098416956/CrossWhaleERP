package com.cross.whale.res;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class StorageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long adminId;

    private String adminName;

    private String createTimeStr;

    private Long cityId;

    private String storageName;

    private Date createTime;

    private Long id;
}
