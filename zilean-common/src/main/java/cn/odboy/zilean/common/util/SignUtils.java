package cn.odboy.zilean.common.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

/**
 * @author: Odboy
 * @time: 2019/7/4 21:39
 * @desc: TODO 签名工具
 */
public final class SignUtils {
    private static final Sign SIGN = SecureUtil.sign(SignAlgorithm.MD5withRSA);

    /**
     * 获取签名Code
     *
     * @param username 账号
     * @param token    token令牌
     * @return String
     */
    public static String signCode(String username, String token) {
        byte[] data = (username + token).getBytes();
        // 签名
        byte[] signed = SIGN.sign(data);
        // Base64编码
        return Base64.encode(signed);
    }

    /**
     * 验证签名
     *
     * @param username 账号
     * @param token    token令牌
     * @param signCode 签名Code
     * @return
     */
    public static boolean verify(String username, String token, String signCode) {
        byte[] data = (username + token).getBytes();
        // 签名
        byte[] signed = Base64.decode(signCode);
        // 验证签名
        return SIGN.verify(data, signed);
    }

    //  public static void main(String[] args) {
    //    String sign = signCode("1943815081", "ebbddcc5317168b747afd89234ea275e");
    //      System.out.println(sign);
    //    boolean result = verify("1943815081", "ebbddcc5317168b747afd89234ea275e", sign);
    //    System.out.println(result);
    //
    //    boolean a =
    //        verify(
    //            "1943815081",
    //                "ebbddcc5317168b747afd89234ea275e",
    //
    // "fVD69HcPaqxRGRYkdCFKPN4DYw8t1yVa/J2ALumUIpG+ag1y9fCZ038gIfpBw5pQoyJ9DLff0UgphsuO+AhU0PtxEykCLvXo8zwMHn6FGvFzwe1D3SBI9tD9MSDe4E2pH3qr+VpyLyLcsEUAZHos4g9uaaBw8hxAyk/8riVLFUE=");
    //    System.out.println(a);
    //  }
}
