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
 * @desc: TODO 字典数据
 * @time: 2019/8/3 7:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "dict_data")
public class DictData extends BaseEntity {
    /**
     * 类型简称(eg. PayMode)
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '类型简称(eg. PayMode)'")
    private String abbr;
    /**
     * 数据标题(eg. 支付宝)
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '数据标题(eg. 支付宝)'")
    private String title;
    /**
     * 数据内容(eg. alipay)
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '数据内容(eg. alipay)'")
    private String value;
}
