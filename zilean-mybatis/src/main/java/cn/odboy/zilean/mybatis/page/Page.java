package cn.odboy.zilean.mybatis.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 页面索引、数据数量
 * @time: 2019/8/8 10:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
public class Page implements Serializable {
    private Integer page = 1;
    private Integer pageSize = 10;
}
