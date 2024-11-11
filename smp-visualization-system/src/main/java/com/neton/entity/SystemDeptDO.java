package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

@Data
@TableName("system_dept")
public class SystemDeptDO extends BaseDO<SystemDeptDO> {
    /**
     *  父ID
     */
    private Long pId;
    /**
     * 部门名称
     */
    private String deptName;
}
