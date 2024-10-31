package com.neton.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author TheSunshine
 * @date 2024-10-30 15:52:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseDO<T extends Model<T>> extends Model<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2548248444612845271L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人id
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建人
     */
    @TableField(value = "create_by_name", fill = FieldFill.INSERT)
    private String createByName;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改人id
     */
    @TableField(value = "modify_by", fill = FieldFill.INSERT_UPDATE)
    private Long modifyBy;

    /**
     * 修改人
     */
    @TableField(value = "modify_by_name", fill = FieldFill.INSERT_UPDATE)
    private String modifyByName;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modify", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModify;

    /**
     * 是否已删除 1-已删除 0-未删除
     */
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    /**
     * 版本号
     */
    @TableField(value = "version", fill = FieldFill.INSERT)
    private Long version;
}
