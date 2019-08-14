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
 * @desc: TODO 操作日志
 * @time: 2019/8/8 2:42
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "operation_log")
public class OperationLog extends BaseEntity {
    /**
     * 操作类型
     */
    @Column(columnDefinition = "TINYINT(1) NOT NULL COMMENT '操作类型'")
    private Byte opType;
    /**
     * 操作内容
     */
    @Column(columnDefinition = "VARCHAR(255) NOT NULL COMMENT '操作内容'")
    private String content;
    /**
     * 操作人
     */
    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '操作人'")
    private Long userId;
}
