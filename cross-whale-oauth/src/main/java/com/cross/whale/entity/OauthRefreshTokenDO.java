package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Blob;

/**
 * @author TheSunshine
 * @date 2024-10-24 11:46:36
 */

@Data
@TableName("oauth_refresh_token")
public class OauthRefreshTokenDO {
    /**
     * 令牌ID
     */
    private String tokenId;
    /**
     * 令牌数据
     */
    private String token;
    /**
     * 认证信息
     */
    private String authentication;
}
