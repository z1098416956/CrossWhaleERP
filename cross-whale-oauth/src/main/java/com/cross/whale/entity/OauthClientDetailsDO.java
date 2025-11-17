package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author TheSunshine
 * @date 2024-10-24 11:39:31
 */
@Data
@TableName("oauth_client_details")
public class OauthClientDetailsDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 客户端ID
     */
    @TableId(value = "client_id", type = IdType.INPUT)
    private String clientId;
    /**
     *资源ID列表
     */
    private String resourceIds;
    /**
     *客户端密钥
     */
    private String clientSecret;
    /**
     *权限范围
     */
    private String scope;
    /**
     *支持的授权类型
     */
    private String authorizedGrantTypes;
    /**
     *重定向URI
     */
    private String webServerRedirectUri;
    /**
     *授权角色
     */
    private String authorities;
    /**
     *访问令牌有效期（秒）
     */
    private Integer accessTokenValidity;
    /**
     *刷新令牌有效期（秒）
     */
    private Integer refreshTokenValidity;
    /**
     *额外信息
     */
    private String additionalInformation;
    /**
     *自动批准的权限范围
     */
    private String autoApproveScopes;
}
