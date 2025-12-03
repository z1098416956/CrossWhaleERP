package com.cross.whale.rocketmq.example;

import com.cross.whale.rocketmq.consumer.BaseRocketMQConsumer;
import com.cross.whale.rocketmq.producer.RocketMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RocketMQ 使用示例
 *
 * @author TheSunshine
 */
@Component
public class RocketMQExample {

    @Autowired
    private RocketMQProducer rocketMQProducer;

    /**
     * 发送消息示例
     */
    public void sendMessageExample() {
        // 发送普通消息
        rocketMQProducer.sendMessage("test-topic", "test-tag", "这是一条测试消息");

        // 发送对象消息
        User user = new User("张三", 25);
        rocketMQProducer.sendMessage("user-topic", "user-create", user);
    }

    /**
     * 用户实体类
     */
    public static class User {
        private String name;
        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    /**
     * 消息消费者示例
     */
    @Component
    public static class UserConsumer extends BaseRocketMQConsumer {

        @Override
        protected String getConsumerGroup() {
            return "user-consumer-group";
        }

        @Override
        protected String getTopic() {
            return "user-topic";
        }

        @Override
        protected String getTagExpression() {
            return "user-create || user-update";
        }

        @Override
        protected boolean handleMessage(String message) {
            try {
                // 解析消息
                User user = parseMessage(message, User.class);

                // 处理消息
                System.out.println("接收到用户信息：" + user.getName() + ", " + user.getAge());

                // 返回 true 表示消息处理成功
                return true;
            } catch (Exception e) {
                // 处理异常，返回 false 表示消息处理失败，稍后会重试
                return false;
            }
        }
    }
}
