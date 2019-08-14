package cn.odboy.zilean.common.util;

import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 集合工具类
 * @time: 2019/8/9 7:06
 * @blog: www.odboy.cn
 */
public final class CollectionsUtils {
    /**
     * 获取集合中第一个对象
     *
     * @param list 对象集合
     * @param <T>  参考对象
     * @return 对象
     */
    public static <T> T getOne(List<T> list) {
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
