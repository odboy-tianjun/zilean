package cn.odboy.zilean.common.util;


import cn.odboy.zilean.common.exception.BusinessException;

/**
 * @author: Odboy
 * @time: 2019/5/24 22:44
 * @desc: TODO 断言工具类
 */
public final class AssertUtils {
    /**
     * 表达式结果真时判断
     *
     * @param expression Boolean
     * @param msg        提示信息
     */
    public static void isTrue(Boolean expression, String msg) {
        if (expression) {
            throw new BusinessException(msg);
        }
    }

    /**
     * 参数为空时
     *
     * @param object 断言对象
     * @param msg    提示信息
     */
    public static void isNull(Object object, String msg) {
        if (object == null) {
            throw new BusinessException(msg);
        }
    }

    /**
     * 参数不空时
     *
     * @param object 断言对象
     * @param msg    提示信息
     */
    public static void notNull(Object object, String msg) {
        if (object != null) {
            throw new BusinessException(msg);
        }
    }
}
