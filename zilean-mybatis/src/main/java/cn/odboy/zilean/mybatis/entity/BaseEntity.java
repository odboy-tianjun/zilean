package cn.odboy.zilean.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO BaseEntity 与数据库表结构一一对应，通过DAO层向上传输数据源对象(即Entity)
 * @time: 2019/8/8 10:42
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNIQUE NOT NULL COMMENT '自增主键'")
    protected Long id;
    /**
     * 创建时间
     */
    @Column(columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    protected Date createTime;
    /**
     * 更新时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '更新时间'")
    protected Date updateTime;
    /**
     * 软删除标识(1、已删除 0、未删除)
     */
    @Column(columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '软删除标识'")
    protected Integer deleteFlag;
    /**
     * 备注信息
     */
    @Column(columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注信息'")
    protected String remark;
}
