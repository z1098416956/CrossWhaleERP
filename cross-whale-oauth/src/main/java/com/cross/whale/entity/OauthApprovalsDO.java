package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author TheSunshine
 * @date 2024-10-24 11:49:47
 */
@Data
@TableName("oauth_approvals")
public class OauthApprovalsDO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     *客户端ID
     */
    private String clientId;
    /**
     *权限范围
     */
    private String scope;
    /**
     *状态
     */
    private String status;
    /**
     *过期时间
     */
    private Date expiresAt;
    /**
     * 最后修改时间
     */
    private Date lastModifiedAt;
}
