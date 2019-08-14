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
 * @desc: TODO 角色权限关联关系
 * @time: 2019/8/3 7:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "role_permission")
public class RolePermission extends BaseEntity {
    /**
     * 角色Id
     */
    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '角色Id'")
    private Long roleId;
    /**
     * 权限Id
     */
    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '权限Id'")
    private Long permissionId;
}
