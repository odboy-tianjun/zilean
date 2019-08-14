package cn.odboy.zilean.common.constant;

/**
 * @author 田俊
 * @version 1.0.0
 * @name: RegexConsts <br/>
 * @description: TODO 常量：正则表达式 <br/>
 * @date: 2019/04/2019/4/1 <br/>
 * @since JDK 1.8
 */
public final class RegexConsts {
    /**
     * 英文单词
     */
    public static final String REGEX_WORD_EN = "^\\w+$";
    /**
     * 中文
     */
    public static final String REGEX_WORD_CN = "[\u4E00-\u9FA5]+";
    /**
     * 6到30位 数字 + 字符 + _，常用于用户名注册
     */
    public static final String REGEX_WORD_EN_6_30 = "^[a-zA-Z][a-zA-Z0-9_]{6,29}$";
    /**
     * 5到20位 数字 + 字符 + _，常用于用户名注册
     */
    public static final String REGEX_WORD_EN_5_20 = "^[a-zA-Z][a-zA-Z0-9_]{5,19}$";
    /**
     * 数字 + 字符 + _，且必须已字母开头
     */
    public static final String REGEX_CHAR_NUM_UL = "^[a-zA-Z][a-zA-Z0-9_]+$";
    /**
     * 数字 + 字母 组合
     */
    public static final String REGEX_CHAR_NUM = "^[A-Za-z0-9]+$";
    /**
     * 字母组合
     */
    public static final String REGEX_CHAR = "^[A-Za-z]+$";
    /**
     * 大写字母组合
     */
    public static final String REGEX_CHAR_UC = "^[A-Z]+$";
    /**
     * 小写字母组合
     */
    public static final String REGEX_CHAR_LC = "^[a-z]+$";
    /**
     * 双字节
     */
    public static final String REGEX_DBCHAR = "[^x00-xff]+";
    /**
     * URL地址： 包括 http, ftp 等
     */
    public static final String REGEX_URL = "[a-zA-z]+://[^s]*";
    /**
     * HTTP URL
     */
    public static final String REGEX_URL_HTTP = "http(s)?://[^s]+";
    /**
     * Internet URL
     */
    public static final String REGEX_URL_INTERNET = "^http(s)?://([\\w-]+.)+[\\w-]+(/[\\w-./?%&=]*)?$";
    /**
     * HTML 标签
     */
    public static final String REGEX_HTML_TAG = "<(\\S*?)[^>]*>.*?|<.*? />";
    /**
     * IP 地址
     */
    public static final String REGEX_IP = "((25[0-5])|(2[0-4]\\d)|(1\\d{2})|([1-9]\\d)|(\\d)).((25[0-5])|(2[0-4]\\d)|(1\\d{2})|([1-9]\\d)|(\\d)).((25[0-5])|(2[0-4]\\d)|(1\\d{2})|([1-9]\\d)|(\\d)).((25[0-5])|(2[0-4]\\d)|(1\\d{2})|([1-9]\\d)|(\\d))";
    /**
     * Email 地址
     */
    public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*";
    /**
     * 中国电话号码
     */
    public static final String REGEX_PHONE_CHINA = "0\\d{2,3}-\\d{7,8}";
    /**
     * 中国移动电话
     */
    public static final String REGEX_MOBILE_CHINA = "1[3|5|8][0-9]-\\d{8}";
    /**
     * 中国身份证号
     */
    public static final String REGEX_IDCARD_CHINA = "\\d{17}[0-9|x|X]|\\d{15}";
    /**
     * 中国邮政编码
     */
    public static final String REGEX_ZIPCODE_CHINA = "\\d{6}";
    /**
     * QQ号
     */
    public static final String REGEX_QQ = "[1-9][0-9]{4,}";
    /**
     * 数字
     */
    public static final String REGEX_CNUMBER = "^[0-9]+$";
    /**
     * 正数
     */
    public static final String REGEX_INT_POSITIVE = "^[1-9]\\d*$";
    /**
     * 非正数
     */
    public static final String REGEX_INT_NOT_POSITIVE = "^-[1-9]\\d*|0$";
    /**
     * 负数
     */
    public static final String REGEX_INT_NEGATIVE = "^-[1-9]\\d*$";
    /**
     * 非负数
     */
    public static final String REGEX_INT_NOT_NEGATIVE = "^[1-9]\\d*|0$";
    /**
     * 整数
     */
    public static final String REGEX_CINT = "^-?[1-9]\\d*$";
    /**
     * 浮点数
     */
    public static final String REGEX_CFLOAT = "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$";
    /**
     * 正浮点数
     */
    public static final String REGEX_FLOAT_POSITIVE = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";
    /**
     * 非正浮点数
     */
    public static final String REGEX_FLOAT_NOT_POSITIVE = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";
    /**
     * 负浮点数
     */

    public static final String REGEX_FLOAT_NEGATIVE = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";
    /**
     * 非负浮点数
     */
    public static final String REGEX_FLOAT_NOT_NEGATIVE = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";
    /**
     * 空行
     */
    public static final String REGEX_SPACE_LINE = "\n\\s*\r";
    /**
     * 空字符
     */
    public static final String REGEX_SPACE_CHAR = "^\\s*|\\s*$";
    /**
     * 关键字标签正则：仅允许输入中文、英文（含大小写）、数字，输入内容必须以逗号隔开（含中英文逗号），但不能以逗号开头或结尾
     * 正确格式示例：99,AB，示例，ss,呵呵
     */
    public static final String REGEX_KEY_WORD_TAG = "^([a-zA-Z0-9\\u4e00-\\u9fa5]+[,|，])*[a-zA-Z0-9\\u4e00-\\u9fa5]+$";
    /**
     * 能输入有1~3位小数的正实数(包含0)
     */
    public static final String REGEX_INT_ARITHMETIC_NUMBER = "^[0-9]+(\\.[0-9]{1,3})?$";
}
