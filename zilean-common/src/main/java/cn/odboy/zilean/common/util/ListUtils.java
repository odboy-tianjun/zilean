package cn.odboy.zilean.common.util;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 田俊
 * @version 1.0.0
 * @name: ListUtils <br/>
 * @description: TODO 数组工具(基于jdk1.8) <br/>
 * @date: 2019/04/2019/4/2 <br/>
 * @since JDK 1.8
 */
@Log4j2
public final class ListUtils {
    public static <K, V> Map<K, V> listToMap(List<V> list, Class clazz, String getKeyMethodName, String getValueMethodName) {
        Map<K, V> result = new HashMap<>(6);
        boolean isOk = list != null && list.size() > 0 && !StringUtils.isEmpty(getKeyMethodName)
                && !StringUtils.isEmpty(getValueMethodName);
        if (isOk) {
            for (Object obj : list) {
                try {
                    Method getKeyMethod = ReflectUtil.getMethod(clazz, getKeyMethodName);
                    Object targetKey = getKeyMethod.invoke(obj);
                    Method getValueMethod = ReflectUtil.getMethod(clazz, getValueMethodName);
                    Object targetValue = getValueMethod.invoke(obj);
                    result.put((K) targetKey, ((V) targetValue));
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage());
                } catch (InvocationTargetException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return result;
    }
}
