package cn.odboy.zilean.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 密码工具类
 * @time: 2019/7/21 18:23
 * @blog: www.odboy.cn
 */
public final class PasswordUtils {
    /**
     * 生成加密密码(理论上可逆，但是当你忘记密码的时候，那就不可逆)
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static String encode(String username, String password) {
        String content = HexUtil.encodeHexStr(username + ":" + password, CharsetUtil.CHARSET_UTF_8);
        // 生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        // 构建
        AES aes;
        if (content.length() == 16) {
            aes = new AES(Mode.CTS, Padding.PKCS5Padding, content.getBytes(), "20190506_Odboy".getBytes());
        } else {
            aes = new AES(Mode.CTS, Padding.PKCS5Padding, content.substring(0, 16).getBytes(), "20190506_OdboyHH".getBytes());
        }
        // 加密
        byte[] encrypt = aes.encrypt(content);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);
        return aes.encryptHex(content);
    }
//
//    public static void main(String[] args) {
//        System.out.println(encode("tianjun", "123456"));
//        System.out.println(encode("odboy", "123456"));
//    }
}
