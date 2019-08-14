package cn.odboy.zilean.common;

import cn.odboy.zilean.common.constant.GlobalConsts;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO Ajax请求返回对象
 * @time: 2019/8/7 8:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
public class HttpResult implements Serializable {
    /**
     * 业务处理成功与否
     */
    private boolean success;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据体
     */
    private Map<String, Object> body = new HashMap<>(GlobalConsts.DEFAULT_COLLECTIONS_SIZE);
}
