package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

@Data
@TableName(value = "areas")
public class AreasDO extends BaseDO<AreasDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;
    /**
     *区域代码
     */
    private String areaId;
    /**
     *区域名称
     */
    private String area;
    /**
     *城市代码
     */
    private String cityId;
}
