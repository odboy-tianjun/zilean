package cn.odboy.zilean.entity;

import cn.odboy.zilean.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 用户
 * @time: 2019/8/3 7:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 邮箱账号
     */
    @Column(columnDefinition = "VARCHAR(20) UNIQUE NOT NULL COMMENT '邮箱账号'")
    private String username;
    /**
     * 密码(加密)
     */
    @Column(columnDefinition = "VARCHAR(18) NOT NULL COMMENT '密码(加密)'")
    private String userpwd;
    /**
     * 头像(JPG，小于500k，70*100px)
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '头像(JPG，小于500k，70*100px)'")
    private String logoUrl;
    /**
     * 性别(0男、1女)
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '性别(0男、1女)'")
    private Byte sex;
    /**
     * 真实姓名
     */
    @Column(columnDefinition = "VARCHAR(8) COMMENT '真实姓名'")
    private String realName;
    /**
     * 生日
     */
    @Column(columnDefinition = "DATE COMMENT '生日'")
    private Date birth;
    /**
     * 联系方式
     */
    @Column(columnDefinition = "VARCHAR(11) COMMENT '联系方式'")
    private String phoneNum;
    /**
     * 参加工作时间
     */
    @Column(columnDefinition = "DATE COMMENT '参加工作时间'")
    private Date joinWorkDate;
    /**
     * 职业
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '职业'")
    private String maritalStatus;
    /**
     * 家乡
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '家乡'")
    private String hometown;
    /**
     * 背景音乐
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '背景音乐'")
    private String bgmUrl;
    /**
     * 签名(座右铭)
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '签名(座右铭)'")
    private String autograph;
    /**
     * 上一次登录时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '上一次登录时间'")
    private Date lastLoginTime;
    /**
     * 角色ID
     */
    @Column(columnDefinition = "BIGINT(20) COMMENT '角色ID'")
    private Long roleId;
}
