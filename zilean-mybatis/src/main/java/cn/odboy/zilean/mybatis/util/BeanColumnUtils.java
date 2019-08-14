package cn.odboy.zilean.mybatis.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 实体类属性名与数据库字段名互转
 * @time: 2019/8/8 10:43
 * @blog: www.odboy.cn
 */
public final class BeanColumnUtils {
    private BeanColumnUtils() {
    }

    /**
     * 类属性名称转SQL字段名
     *
     * @param beanPropName 类属性名称
     * @return
     */
    public static String getDataBaseColumnName(String beanPropName) {
        if (!StringUtils.isEmpty(beanPropName)) {
            StringBuilder sb = new StringBuilder();
            for (char c : beanPropName.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    sb.append("_").append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        throw new NullPointerException("beanPropName不能为null");
    }

    /**
     * 返回有的值条件,比如有个User类，有属性name,id
     * 当只有id有值时，返回and id ={id的值}
     *
     * @return
     * @throws
     * @author ld
     * @date 2019/02/11 14:13
     */
    public String gainConditionFromObjectByField(Object object) {
        StringBuilder sb = new StringBuilder();
        // 取出所有属性
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            // 取属性值
            Object value = getFieldValue(this, fieldName);
            if (ObjectUtil.isNotNull(value)) {
                Class<?> type = f.getType();
                if ("class java.lang.String".equals(type.toString())) {
                    sb.append(" and ").append(fieldName).append("='").append(value).append("'");
                } else {
                    sb.append(" and ").append(fieldName).append("=").append(value);
                }
            }
            continue;
        }
        return sb.toString();
    }

    /**
     * 获取字段值
     *
     * @param object    对象
     * @param fieldName 类的属性名称
     * @return 字段值
     */
    private String getFieldValue(Object object, String fieldName) {
        Object o = invokeMethod(object, fieldName);
        return ObjectUtil.isNotNull(o) ? o.toString() : null;
    }


    /**
     * 执行某个Field的getField方法
     *
     * @param object    对象
     * @param fieldName 类的属性名称
     * @return Object
     */
    private Object invokeMethod(Object object, String fieldName) {
        Class<?> testClass = object.getClass();
        // 首字母大写
        // fieldName -> FieldName
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return ReflectUtil.invoke(testClass, "get" + methodName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 首字母转大写
     *
     * @param s String
     * @return String
     */
    public static String toUpperCaseFirstOne(String s) {

        if (Character.isUpperCase(s.charAt(0))) {
            // 如果第一位为大写字母, 直接返回
            return s;
        } else {
            return String.valueOf(Character.toUpperCase(s.charAt(0))) + s.substring(1);
        }
    }
}
