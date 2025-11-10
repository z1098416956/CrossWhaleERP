package com.neton.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class GeneratedBarcodeUtils {
 
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
}
