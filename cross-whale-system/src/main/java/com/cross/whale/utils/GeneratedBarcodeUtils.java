package com.cross.whale.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class GeneratedBarcodeUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 机器ID占用的位数
    private static final long MACHINE_BIT = 4;
    // 序列号占用的位数
    private static final long SEQUENCE_BIT = 7;
    // 机器ID最大值
    private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    // 序列号最大值
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    // 机器ID (0-15)
    private final long machineId;
    // 序列号
    private long sequence = 0L;
    // 上次时间戳
    private long lastTimestamp = -1L;

    public GeneratedBarcodeUtils() {
        this.machineId = getMachineId();
    }

    /**
     * 生成对应的条码
     * @param moduleName 模块名称，用于区分不同业务的条码
     * @return 格式为yyyyMMdd+4位序号的条码，如20251110001
     */
    public String generatedBarcode(String moduleName){
        // 获取当前日期，格式为yyyyMMdd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(new Date());

        // 构建Redis键名，用于存储当天的序号
        String redisKey = "barcode:" + moduleName + ":" + currentDate;

        // 使用Redis的INCR原子操作获取当天序号
        Long sequence = stringRedisTemplate.opsForValue().increment(redisKey);

        // 如果是第一次设置，设置过期时间为24小时
        if (sequence != null && sequence == 1) {
            stringRedisTemplate.expire(redisKey, 24, TimeUnit.HOURS);
        }

        // 将序号格式化为4位数字，不足补0
        String sequenceStr = String.format("%04d", sequence);

        // 组合日期和序号生成最终条码
        return moduleName + currentDate + sequenceStr;
    }

    /**
     * 获取机器ID
     * @return 机器ID (0-15)
     */
    private long getMachineId() {
        try {
            // 获取本机IP地址的最后一个字节作为机器ID
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            if (hostAddress != null && hostAddress.contains(".")) {
                String[] parts = hostAddress.split("\\.");
                if (parts.length == 4) {
                    // 取IP地址的最后一个字节，并限制在0-15范围内
                    return Long.parseLong(parts[3]) & MAX_MACHINE_NUM;
                }
            }
        } catch (UnknownHostException e) {
            // 忽略异常，使用默认值
        }
        // 如果获取失败，使用随机值
        return (long) (Math.random() * MAX_MACHINE_NUM);
    }

    /**
     * 生成单据号
     * @param moduleName 模块名称，用于区分不同业务的单据号
     * @return 格式为11位数字的单据号，如00001495192
     */
    public String generateReceipts(String moduleName){
        // 获取当前时间戳（秒级）
        long currentTimestamp = System.currentTimeMillis() / 1000;

        // 如果当前时间小于上一次时间，说明时钟回拨
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨，拒绝生成单据号");
        }

        // 如果是同一时间戳内，则序列号递增
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 序列号溢出
            if (sequence == 0) {
                // 等待下一秒
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 不同时间戳，序列号重置为0
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // 获取时间戳的后5位（确保在0-99999范围内）
        long timestampPart = currentTimestamp % 100000;

        // 组合：时间戳(5位) + 机器ID(1位) + 序列号(3位) + 随机数(2位)
        long randomPart = (long) (Math.random() * 100);
        long id = timestampPart * 1000000 + machineId * 100000 + sequence * 100 + randomPart;

        // 格式化为11位数字，不足补0
        return moduleName + String.format("%011d", id);
    }

    /**
     * 等待下一毫秒
     * @param lastTimestamp 上次时间戳
     * @return 新的时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis() / 1000;
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis() / 1000;
        }
        return timestamp;
    }
}
