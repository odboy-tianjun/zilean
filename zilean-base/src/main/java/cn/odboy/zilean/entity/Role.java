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
 * @desc: TODO 角色
 * @time: 2019/8/3 7:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    /**
     * 角色名称(eg. 超级管理员)
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '角色名称(eg. 超级管理员)'")
    private String abbr;
    /**
     * 角色简称(eg. admin)
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '角色简称(eg. admin)'")
    private String title;
    /**
     * 角色描述(eg. 拥有至高权限)
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '角色描述(eg. 拥有至高权限)'")
    private String value;
}
