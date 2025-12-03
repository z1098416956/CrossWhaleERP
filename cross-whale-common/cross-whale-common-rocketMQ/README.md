# RocketMQ 通用模块

本模块提供了 RocketMQ 5.3.2 的集成支持，其他模块只需引入依赖即可使用。

## 使用方法

### 1. 添加依赖

在其他模块的 `pom.xml` 中添加以下依赖：

```xml
<dependency>
    <groupId>com.neton</groupId>
    <artifactId>cross-whale-common-rocketMQ</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 2. 配置 application.yml

在 `application.yml` 中添加 RocketMQ 配置：

```yaml
rocketmq:
  name-server: 127.0.0.1:9876  # RocketMQ NameServer 地址
  producer-group: default_producer_group  # 生产者组名（可选，默认为 default_producer_group）
  send-message-timeout: 3000  # 消息发送超时时间，单位：毫秒（可选，默认为 3000）
  max-message-size: 4194304  # 消息最大长度，单位：字节（可选，默认为 4MB）
  retry-times-when-send-failed: 2  # 重试次数（可选，默认为 2）
```

### 3. 使用生产者发送消息

注入 `RocketMQProducer` 并使用：

```java
@Service
public class UserService {
    
    @Autowired
    private RocketMQProducer rocketMQProducer;
    
    public void createUser(User user) {
        // 保存用户到数据库
        // ...
        
        // 发送用户创建消息
        rocketMQProducer.sendMessage("user-topic", "user-create", user);
    }
}
```

### 4. 创建消费者

继承 `BaseRocketMQConsumer` 并实现抽象方法：

```java
@Component
public class UserConsumer extends BaseRocketMQConsumer {
    
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
```

## 高级用法

### 顺序消息

如果需要顺序消费消息，重写 `isOrderly()` 方法并返回 `true`：

```java
@Override
protected boolean isOrderly() {
    return true;
}
```

### 自定义消费起始位置

重写 `getConsumeFromWhere()` 方法：

```java
@Override
protected ConsumeFromWhere getConsumeFromWhere() {
    return ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET;
}
```

### 异步发送消息

```java
rocketMQProducer.sendMessageAsync("topic", "tag", message, new SendCallback() {
    @Override
    public void onSuccess(SendResult sendResult) {
        // 发送成功回调
        System.out.println("消息发送成功：" + sendResult.getMsgId());
    }
    
    @Override
    public void onException(Throwable e) {
        // 发送失败回调
        System.err.println("消息发送失败：" + e.getMessage());
    }
});
```

### 单向发送消息

```java
rocketMQProducer.sendMessageOneway("topic", "tag", message);
```

## 注意事项

1. 确保配置了正确的 RocketMQ NameServer 地址
2. 消息体对象需要能够被 JSON 序列化和反序列化
3. 消费者处理消息时，如果返回 `false`，消息将会被重新消费
4. 顺序消费时，如果消息处理失败，会挂起当前队列一段时间后继续消费