package com.cross.whale.consumer;

import com.cross.whale.rocketmq.consumer.BaseRocketMQConsumer;
import com.cross.whale.service.ReceiptsInfoService;
import com.cross.whale.utils.PurchaseSalesRocketMQConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 请购单采购状态消费者
 */
@Component
public class ReceiptsStatusConsumer extends BaseRocketMQConsumer {

    @Autowired
    private ReceiptsInfoService receiptsInfoService;

    /**
     * 获取消费者组名
     *
     * @return 消费者组名
     */
    @Override
    protected String getConsumerGroup() {
        return PurchaseSalesRocketMQConstants.RECEIPTS_STATUS_GROUP;
    }

    /**
     * 获取订阅主题
     *
     * @return 订阅主题
     */
    @Override
    protected String getTopic() {
        return PurchaseSalesRocketMQConstants.RECEIPTS_STATUS_TOPIC;
    }

    /**
     * 获取订阅标签表达式
     *
     * @return 订阅标签表达式
     */
    @Override
    protected String getTagExpression() {
        return PurchaseSalesRocketMQConstants.RECEIPTS_STATUS_TAG;
    }

    /**
     * 处理消息
     *
     * @param message 消息内容
     * @return 消费结果
     */
    @Override
    protected boolean handleMessage(String message) {
        receiptsInfoService.updateReceiptsStatusByPurchaseNumber(message);
        return true;
    }
}
