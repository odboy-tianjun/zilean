package cn.odboy.zilean.map;

import cn.odboy.zilean.bo.PermissionBo;
import cn.odboy.zilean.entity.Permission;
import org.mapstruct.Mapper;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO PermissionBo转换器
 * @time: 2019/8/8 3:48
 * @blog: www.odboy.cn
 */
@Mapper(componentModel = "spring")
public interface PermissionBoMapper {
    /**
     * PermissionBo 转 Permission
     *
     * @param permissionBo PermissionBo
     * @return Permission
     */
    Permission boToPermission(PermissionBo permissionBo);

    /**
     * Permission 转 PermissionBo
     *
     * @param permission Permission
     * @return PermissionBo
     */
    PermissionBo permissionToBo(Permission permission);
}
