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
 * @desc: TODO 权限(可访问菜单)
 * @time: 2019/8/3 7:43
 * @blog: www.odboy.cn
 * <p>
 * 字段 type 是标示是菜单资源还是普通资源
 * 菜单资源的意义就是导航菜单,会显示到左侧导航,
 * 普通资源就是菜单内的按钮或者提交路径,
 * 这个时候 pid的意义就比较重要了,pid就是上级菜单的id,
 * 对于页面普通资源来说,就是相应的导航菜单的url
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {
    /**
     * 父ID
     */
    @Column(columnDefinition = "BIGINT(20) COMMENT '父ID'")
    private Long pid;
    /**
     * 标题
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '标题'")
    private String title;
    /**
     * 权限编码(eg. user:list)
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '权限编码(eg. user:list)'")
    private String code;
    /**
     * 页面URL
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '页面URL'")
    private String pageUrl;
    /**
     * 资源类型
     * 0、菜单资源 1、普通资源
     */
    @Column(columnDefinition = "TINYINT(1) NOT NULL COMMENT '0、菜单资源 1、普通资源'")
    private Byte type;
    /**
     * 菜单图标
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '菜单图标'")
    private String ico;
    /**
     * 资源是否可用
     * 0、禁用 1、启用
     */
    @Column(columnDefinition = "TINYINT(1) NOT NULL COMMENT '资源是否可用'")
    private Byte status;
}
