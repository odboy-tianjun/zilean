package cn.odboy.zilean.common.exception;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 业务异常
 * @time: 2019/8/8 10:40
 * @blog: www.odboy.cn
 */
public class BusinessException extends RuntimeException {
    /**
     * 无参构造
     */
    public BusinessException() {
        super();
    }

    /**
     * 含参构造
     *
     * @param message 异常信息
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * 含参构造
     *
     * @param message 异常信息
     * @param cause   抛出异常
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 含参构造
     *
     * @param cause 抛出异常
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }
}
