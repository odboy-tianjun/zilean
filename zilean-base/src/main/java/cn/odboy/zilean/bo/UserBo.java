package cn.odboy.zilean.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 用户Bo
 * @time: 2019/8/9 6:19
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
public class UserBo implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String encryptPwd;
    private String salt;
    private List<String> roles;
}
