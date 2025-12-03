package com.cross.whale.rocketmq.autoconfigure;

import com.cross.whale.rocketmq.config.RocketMQConfig;
import com.cross.whale.rocketmq.producer.RocketMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * RocketMQ 自动配置类
 *
 * @author TheSunshine
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "rocketmq", name = "name-server")
@Import({RocketMQConfig.class})
@ComponentScan(basePackages = "com.cross.whale.rocketmq")
public class RocketMQAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQAutoConfiguration.class);

    @Bean
    public RocketMQProducer rocketMQProducer() {
        logger.info("正在初始化 RocketMQ 生产者工具类...");
        return new RocketMQProducer();
    }
}
