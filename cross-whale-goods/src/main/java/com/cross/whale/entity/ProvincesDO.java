package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

@Data
@TableName(value = "provinces")
public class ProvincesDO extends BaseDO<ProvincesDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     *省份名称
     */
    private String province;
    /**
     *省份代码
     */
    private String provinceId;
}
