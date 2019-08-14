package cn.odboy.zilean.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 田俊
 * @version 1.0.0
 * @name: CookieUtils <br/>
 * @description: TODO Cookie工具类 <br/>
 * @date: 2019/03/2019/3/30 <br/>
 * @since JDK 1.8
 */
public final class CookieUtils {
    /**
     * 读取cookie数组，之后迭代出各个cookie
     *
     * @param request HttpServletRequest
     * @return Cookie[]
     */
    public static Cookie[] getCookies(HttpServletRequest request) {
        // 根据请求数据，找到cookie数组
        return request.getCookies();
    }

    /**
     * 读取cookie
     *
     * @param request    HttpServletRequest
     * @param cookieName cookie名称
     * @return cookie值
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        //根据请求数据，找到cookie数组
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie != null && cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 创建cookie，并将新cookie添加到“响应对象”response中。
     *
     * @param response    HttpServletResponse
     * @param cookieName  cookie名称
     * @param cookieValue cookie值
     */
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue) {
        // 创建新cookie
        Cookie cookie = new Cookie(cookieName, cookieValue);
        // 设置存在时间为无限
        cookie.setMaxAge(-1);
        // 设置作用域
        cookie.setPath("/");
        // 将cookie添加到response的cookie数组中返回给客户端
        response.addCookie(cookie);
    }

    /**
     * 修改cookie，可以根据某个cookie的name修改它（不只是name要与被修改cookie一致，path、domain必须也要与被修改cookie一致）
     *
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param cookieName  cookie名称
     * @param cookieValue cookie值
     * @return 是否修改成功
     */
    public static boolean editCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return false;
        } else {
            for (Cookie cookie : cookies) {
                // 迭代时如果发现与指定cookieName相同的cookie，就修改相关数据
                if (cookie.getName().equals(cookieName)) {
                    // 修改value
                    cookie.setValue(cookieValue);
                    cookie.setPath("/");
                    // 修改存活时间
                    cookie.setMaxAge(-1);
                    // 将修改过的cookie存入response，替换掉旧的同名cookie
                    response.addCookie(cookie);
                    break;
                }
            }
            return true;
        }
    }

    /**
     * 删除cookie
     *
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param cookieName cookie名称
     * @return 是否删除成功
     */
    public static boolean delCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return false;
        } else {
            for (Cookie cookie : cookies) {
                // 如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
            return true;
        }
    }
}
