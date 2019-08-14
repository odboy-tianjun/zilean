package cn.odboy.zilean.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 田俊
 * @version 1.0.0
 * @name: HttpRequestUtils <br/>
 * @description: TODO 请求处理 <br/>
 * @date: 2019/04/2019/4/12 <br/>
 * @since JDK 1.8
 */
public final class HttpRequestUtils {
    /**
     * 获取webapp请求路径
     *
     * @param request
     * @return
     */
    public static String getRequestUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    }
}
