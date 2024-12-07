package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

@Data
@TableName("storage")
public class StorageDO extends BaseDO<StorageDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;
    /**
     *仓库名称
     */
    private String storageName;
    /**
     *管理员id
     */
    private Long adminId;
    /**
     *城市sid
     */
    private Long cityId;


}
