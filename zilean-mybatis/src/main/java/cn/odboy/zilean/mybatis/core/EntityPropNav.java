package cn.odboy.zilean.mybatis.core;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 对象属性名称和值(容器)
 * @time: 2019/8/10 3:37
 * @blog: www.odboy.cn
 */
@Getter
@ToString
@Log4j2
public class EntityPropNav implements Serializable {
    private static EntityPropNav cloneObj = new EntityPropNav();

    /**
     * 对象属性名称
     */
    public String name;
    /**
     * 对象属性值
     */
    public Object value;

    /**
     * 私有化构造函数，不得从外部new
     */
    private EntityPropNav() {

    }

    /**
     * 获取对象克隆实例, 适合频繁new的场景
     *
     * @param name  对象属性名称
     * @param value 对象属性值
     * @return EntityPropNav
     */
    public static EntityPropNav build(String name, Object value) {
        return ObjectUtil.cloneByStream(cloneObj).setName(name).setValue(value);
    }

    /**
     * 设置对象属性名称
     *
     * @param name 对象属性名称
     */
    public EntityPropNav setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 设置对象属性值
     *
     * @param value 对象属性值
     */
    public EntityPropNav setValue(Object value) {
        this.value = value;
        return this;
    }
}
