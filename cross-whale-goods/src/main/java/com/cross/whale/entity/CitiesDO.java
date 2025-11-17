package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;
@Data
@TableName(value = "cities")
public class CitiesDO extends BaseDO<CitiesDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     *城市代码
     */
    private String cityId;
    /**
     *城市名称
     */
    private String city;
    /**
     *省份代码
     */
    private String provinceId;
}
