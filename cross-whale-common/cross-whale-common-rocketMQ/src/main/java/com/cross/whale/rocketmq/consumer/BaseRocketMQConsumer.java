package com.cross.whale.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * RocketMQ 消息消费者基类
 *
 * @author TheSunshine
 */
@Configuration
@ConditionalOnProperty(prefix = "rocketmq", name = "name-server")
public abstract class BaseRocketMQConsumer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${rocketmq.name-server}")
    private String nameServer;

    protected DefaultMQPushConsumer consumer;

    /**
     * 获取消费者组名
     *
     * @return 消费者组名
     */
    protected abstract String getConsumerGroup();

    /**
     * 获取订阅主题
     *
     * @return 订阅主题
     */
    protected abstract String getTopic();

    /**
     * 获取订阅标签表达式
     *
     * @return 订阅标签表达式
     */
    protected abstract String getTagExpression();

    /**
     * 处理消息
     *
     * @param message 消息内容
     * @return 消费结果
     */
    protected abstract boolean handleMessage(String message);

    /**
     * 获取消息消费模式（顺序消费或并发消费）
     *
     * @return true: 顺序消费, false: 并发消费
     */
    protected boolean isOrderly() {
        return false;
    }

    /**
     * 获取消息消费起始位置
     *
     * @return 消费起始位置
     */
    protected ConsumeFromWhere getConsumeFromWhere() {
        return ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
    }

    /**
     * 初始化消费者
     */
    @PostConstruct
    public void init() {
        try {
            // 创建消费者实例
            consumer = new DefaultMQPushConsumer(getConsumerGroup());

            // 设置 NameServer 地址
            consumer.setNamesrvAddr(nameServer);

            // 设置消费起始位置
            consumer.setConsumeFromWhere(getConsumeFromWhere());

            // 设置分配策略
            consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely());

            // 订阅主题和标签
            consumer.subscribe(getTopic(), getTagExpression());

            // 注册消息监听器
            if (isOrderly()) {
                // 顺序消费
                consumer.registerMessageListener((MessageListenerOrderly) (messages, context) -> {
                    for (MessageExt messageExt : messages) {
                        try {
                            String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
                            logger.info("接收到顺序消息，主题：{}，标签：{}，消息ID：{}，消息内容：{}",
                                    messageExt.getTopic(), messageExt.getTags(), messageExt.getMsgId(), messageBody);

                            boolean result = handleMessage(messageBody);

                            if (result) {
                                logger.info("顺序消息处理成功，消息ID：{}", messageExt.getMsgId());
                                return ConsumeOrderlyStatus.SUCCESS;
                            } else {
                                logger.error("顺序消息处理失败，消息ID：{}", messageExt.getMsgId());
                                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                            }
                        } catch (Exception e) {
                            logger.error("顺序消息处理异常，消息ID：{}", messageExt.getMsgId(), e);
                            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                        }
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                });
            } else {
                // 并发消费
                consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
                    for (MessageExt messageExt : messages) {
                        try {
                            String messageBody = new String(messageExt.getBody(), StandardCharsets.UTF_8);
                            logger.info("接收到并发消息，主题：{}，标签：{}，消息ID：{}，消息内容：{}",
                                    messageExt.getTopic(), messageExt.getTags(), messageExt.getMsgId(), messageBody);

                            boolean result = handleMessage(messageBody);

                            if (result) {
                                logger.info("并发消息处理成功，消息ID：{}", messageExt.getMsgId());
                            } else {
                                logger.error("并发消息处理失败，消息ID：{}", messageExt.getMsgId());
                                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                            }
                        } catch (Exception e) {
                            logger.error("并发消息处理异常，消息ID：{}", messageExt.getMsgId(), e);
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                });
            }

            // 启动消费者
            consumer.start();

            logger.info("RocketMQ 消费者启动成功，消费者组：{}，主题：{}，标签：{}",
                    getConsumerGroup(), getTopic(), getTagExpression());
        } catch (Exception e) {
            logger.error("RocketMQ 消费者启动失败", e);
            throw new RuntimeException("RocketMQ 消费者启动失败", e);
        }
    }

    /**
     * 销毁消费者
     */
    @PreDestroy
    public void destroy() {
        if (consumer != null) {
            consumer.shutdown();
            logger.info("RocketMQ 消费者已关闭，消费者组：{}", getConsumerGroup());
        }
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json JSON 字符串
     * @param clazz 目标类型
     * @param <T> 泛型类型
     * @return 转换后的对象
     */
    protected <T> T parseMessage(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            logger.error("消息解析失败，JSON：{}，目标类型：{}", json, clazz.getName(), e);
            throw new RuntimeException("消息解析失败", e);
        }
    }
}
