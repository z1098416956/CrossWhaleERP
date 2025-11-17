package com.cross.whale.bean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.cross.whale.utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class NetonBeanUtils {

    public static <T> T toBean(Object source, Class<T> targetClass) {
        return BeanUtil.toBean(source, targetClass);
    }

    public static <T> T toBean(Object source, Class<T> targetClass, Consumer<T> peek) {
        T target = toBean(source, targetClass);
        if (target != null) {
            peek.accept(target);
        }
        return target;
    }

    public static <S, T> List<T> toBean(List<S> source, Class<T> targetType) {
        if (source == null) {
            return null;
        }
        return CollectionUtils.convertList(source, s -> toBean(s, targetType));
    }

    public static <S, T> List<T> toBean(List<S> source, Class<T> targetType, Consumer<T> peek) {
        List<T> list = toBean(source, targetType);
        if (list != null) {
            list.forEach(peek);
        }
        return list;
    }


    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtil.copyProperties(source, target, false);
    }

    /**
     * 复制源对象中非空属性到目标对象
     *
     * @param src    源对象
     * @param target 目标对象
     */
    public static void copyNonNullProperties(Object src, Object target) {
        // 将源对象转换为 Map，排除空值
        Map<String, Object> srcMap = BeanUtil.beanToMap(src, false, true);
        // 复制非空属性
        BeanUtil.copyProperties(srcMap, target, CopyOptions.create().create().setIgnoreNullValue(true));
    }
}
