package com.neton.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.data.elasticsearch")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ElasticSearchProperties {

    /**
     * 是否启用
     */
    private String[] hostAndPorts;
    /**
     * 用户名
     */
   // private String username;
    /**
     * 密码
     */
   // private String password;
    /**
     * CA证书指纹
     */
   // private String caFingerprint;
}
