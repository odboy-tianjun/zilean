package cn.odboy.zilean.map;

import cn.odboy.zilean.bo.UserBo;
import cn.odboy.zilean.entity.User;
import org.mapstruct.Mapper;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO UserBo转换器
 * @time: 2019/8/8 3:48
 * @blog: www.odboy.cn
 */
@Mapper(componentModel = "spring")
public interface UserBoMapper {
    /**
     * UserBo 转 User
     *
     * @param userBo UserBo
     * @return User
     */
    User boToUser(UserBo userBo);

    /**
     * User 转 UserBo
     *
     * @param user User
     * @return UserBo
     */
    UserBo userToBo(User user);
}
