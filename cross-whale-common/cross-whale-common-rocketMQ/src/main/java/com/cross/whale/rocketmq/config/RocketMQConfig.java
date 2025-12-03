package com.cross.whale.rocketmq.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ 配置类
 *
 * @author TheSunshine
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQConfig {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQConfig.class);

    /**
     * NameServer 地址
     */
    private String nameServer;

    /**
     * 生产者组名
     */
    private String producerGroup = "default_producer_group";

    /**
     * 消息发送超时时间，单位：毫秒
     */
    private int sendMessageTimeout = 3000;

    /**
     * 消息最大长度，单位：字节
     */
    private int maxMessageSize = 1024 * 1024 * 4; // 4MB

    /**
     * 重试次数
     */
    private int retryTimesWhenSendFailed = 2;

    /**
     * 异步发送重试次数
     */
    private int retryTimesWhenSendAsyncFailed = 2;

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq", name = "name-server")
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        logger.info("正在初始化 RocketMQ 生产者...");

        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(nameServer);
        producer.setSendMsgTimeout(sendMessageTimeout);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendAsyncFailed);

        // 启动生产者
        producer.start();
        logger.info("RocketMQ 生产者初始化成功，生产者组：{}", producerGroup);

        return producer;
    }

    // Getters and Setters
    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public int getSendMessageTimeout() {
        return sendMessageTimeout;
    }

    public void setSendMessageTimeout(int sendMessageTimeout) {
        this.sendMessageTimeout = sendMessageTimeout;
    }

    public int getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public int getRetryTimesWhenSendFailed() {
        return retryTimesWhenSendFailed;
    }

    public void setRetryTimesWhenSendFailed(int retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
    }

    public int getRetryTimesWhenSendAsyncFailed() {
        return retryTimesWhenSendAsyncFailed;
    }

    public void setRetryTimesWhenSendAsyncFailed(int retryTimesWhenSendAsyncFailed) {
        this.retryTimesWhenSendAsyncFailed = retryTimesWhenSendAsyncFailed;
    }
}
