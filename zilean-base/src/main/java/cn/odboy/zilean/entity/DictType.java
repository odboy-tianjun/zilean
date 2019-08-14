package cn.odboy.zilean.entity;

import cn.odboy.zilean.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 字典类型
 * @time: 2019/8/3 7:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "dict_type")
public class DictType extends BaseEntity {
    /**
     * 类型名称(eg. 付款方式)
     */
    @Column(columnDefinition = "VARCHAR(50) UNIQUE NOT NULL COMMENT '类型名称(eg. 付款方式)'")
    private String name;
    /**
     * 类型简称(eg. PayMode)
     */
    @Column(columnDefinition = "VARCHAR(50) UNIQUE NOT NULL COMMENT '类型简称(eg. PayMode)'")
    private String abbr;
}
