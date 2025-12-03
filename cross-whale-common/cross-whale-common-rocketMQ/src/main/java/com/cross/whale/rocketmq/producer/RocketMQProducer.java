package com.cross.whale.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * RocketMQ 消息生产者工具类
 *
 * @author TheSunshine
 */
@Component
public class RocketMQProducer {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQProducer.class);

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    /**
     * 同步发送消息
     *
     * @param topic 主题
     * @param tag 标签
     * @param messageBody 消息体
     * @return 发送结果
     */
    public SendResult sendMessage(String topic, String tag, Object messageBody) {
        try {
            // 将消息对象转换为 JSON 字符串
            String messageJson = JSON.toJSONString(messageBody);

            // 创建消息实例
            Message message = new Message(topic, tag, messageJson.getBytes(StandardCharsets.UTF_8));

            // 发送消息
            SendResult sendResult = defaultMQProducer.send(message);

            logger.info("消息发送成功，主题：{}，标签：{}，消息ID：{}", topic, tag, sendResult.getMsgId());

            return sendResult;
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            logger.error("消息发送失败，主题：{}，标签：{}", topic, tag, e);
            throw new RuntimeException("消息发送失败", e);
        }
    }

    /**
     * 同步发送消息（无标签）
     *
     * @param topic 主题
     * @param messageBody 消息体
     * @return 发送结果
     */
    public SendResult sendMessage(String topic, Object messageBody) {
        return sendMessage(topic, null, messageBody);
    }

    /**
     * 异步发送消息
     *
     * @param topic 主题
     * @param tag 标签
     * @param messageBody 消息体
     * @param sendCallback 回调函数
     */
    public void sendMessageAsync(String topic, String tag, Object messageBody, SendCallback sendCallback) {
        try {
            // 将消息对象转换为 JSON 字符串
            String messageJson = JSON.toJSONString(messageBody);

            // 创建消息实例
            Message message = new Message(topic, tag, messageJson.getBytes(StandardCharsets.UTF_8));

            // 异步发送消息
            defaultMQProducer.send(message, sendCallback);

            logger.info("异步消息发送请求已提交，主题：{}，标签：{}", topic, tag);
        } catch (MQClientException | RemotingException | InterruptedException e) {
            logger.error("异步消息发送失败，主题：{}，标签：{}", topic, tag, e);
            throw new RuntimeException("异步消息发送失败", e);
        }
    }

    /**
     * 异步发送消息（无标签）
     *
     * @param topic 主题
     * @param messageBody 消息体
     * @param sendCallback 回调函数
     */
    public void sendMessageAsync(String topic, Object messageBody, SendCallback sendCallback) {
        sendMessageAsync(topic, null, messageBody, sendCallback);
    }

    /**
     * 单向发送消息（不关心发送结果）
     *
     * @param topic 主题
     * @param tag 标签
     * @param messageBody 消息体
     */
    public void sendMessageOneway(String topic, String tag, Object messageBody) {
        try {
            // 将消息对象转换为 JSON 字符串
            String messageJson = JSON.toJSONString(messageBody);

            // 创建消息实例
            Message message = new Message(topic, tag, messageJson.getBytes(StandardCharsets.UTF_8));

            // 单向发送消息
            defaultMQProducer.sendOneway(message);

            logger.info("单向消息发送请求已提交，主题：{}，标签：{}", topic, tag);
        } catch (MQClientException | RemotingException | InterruptedException e) {
            logger.error("单向消息发送失败，主题：{}，标签：{}", topic, tag, e);
            throw new RuntimeException("单向消息发送失败", e);
        }
    }

    /**
     * 单向发送消息（无标签）
     *
     * @param topic 主题
     * @param messageBody 消息体
     */
    public void sendMessageOneway(String topic, Object messageBody) {
        sendMessageOneway(topic, null, messageBody);
    }
}
