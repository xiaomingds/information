package com.example.information.util.SM3;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;

/**
 * @ClassName SM3Util
 * @Author xiaomingds
 * @Date 2021/4/20 19:29
 **/
@Slf4j
public class SM3Util {

    private static final String ENCODING = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    /**
     * 密码加密方法总和
     * @param pwd 密码
     * @return 逻辑加密后的返回密码
     */
    public static String pwdEncrypt(String pwd){
        if (StringUtils.isNotEmpty(pwd)){
            String pwdConvert = pwdEncryptAssembly(pwd);
            String encryptPwd = encrypt(pwdConvert);
            return encryptPwd;
        }
        throw new IllegalStateException("密码不能为空!");
    }

    /**
     * 获取前端传来的密码数字，然后加密拼接
     * @param pwd 密码
     * @return 加密拼接完的密码
     */
    public static String pwdEncryptAssembly(String pwd) {
        StringBuffer stringPwd = new StringBuffer();
        for (char key : pwd.toCharArray()) {
            String s = SM3Rule.valueOfCode(String.valueOf(key));
            String encrypt = encrypt(s);
            stringPwd.append(encrypt);
        }
        String jsonPwd = stringPwd.toString();
        return jsonPwd.toUpperCase();
    }

    /**
     * sm3算法加密
     *
     * @param paramStr 待加密字符串
     * @return 返回加密后，固定长度=32的16进制字符串
     */
    public static String encrypt(String paramStr) {
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";
        try {
            // 将字符串转换成byte数组
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 调用hash()
            byte[] resultHash = hash(srcData);
            // 将返回的hash值转换成16进制字符串
            resultHexString = ByteUtils.toHexString(resultHash);
            return resultHexString.toUpperCase();
        } catch (UnsupportedEncodingException e) {
            log.info("SM3算法加密失败：",e);
            throw new  IllegalStateException("SM3算法加密异常");
        }

    }

    /**
     * 返回长度=32的byte数组
     *
     * @param srcData
     * @return 数组
     * @explain 生成对应的hash值
     */
    public static byte[] hash(byte[] srcData) {

        try {
            SM3Digest digest = new SM3Digest();
            digest.update(srcData, 0, srcData.length);
            byte[] hash = new byte[digest.getDigestSize()];
            digest.doFinal(hash, 0);
            return hash;
        }catch (Exception e){
            log.info("返回byte数组失败：",e);
            throw new IllegalStateException("返回byte数组异常");
        }
    }

    /**
     * 通过密钥进行加密
     *
     * @param key     密钥
     * @param srcData 被加密的byte数组
     * @return
     * @explain 指定密钥进行加密
     */
    public static byte[] hmac(byte[] key, byte[] srcData) {

        try {
            KeyParameter keyParameter = new KeyParameter(key);
            SM3Digest digest = new SM3Digest();
            HMac mac = new HMac(digest);
            mac.init(keyParameter);
            mac.update(srcData, 0, srcData.length);
            byte[] result = new byte[mac.getMacSize()];
            mac.doFinal(result, 0);
            return result;
        }catch (Exception e){
            log.info("通过秘钥加密失败：",e);
            throw new IllegalStateException("通过秘钥加密异常");
        }
    }

    /**
     * 判断源数据与加密数据是否一致
     *
     * @param srcStr       原字符串
     * @param sm3HexString 16进制字符串
     * @return 校验结果
     * @explain 通过验证原数组和生成的hash数组是否为同一数组，验证2者是否为同一数据
     */
    public static boolean verify(String srcStr, String sm3HexString) {
        boolean flag = false;
        try {
            byte[] srcData = srcStr.getBytes(ENCODING);
            byte[] sm3Hash = ByteUtils.fromHexString(sm3HexString);
            byte[] newHash = hash(srcData);
            if (Arrays.equals(newHash, sm3Hash)) {
                flag = true;
            }
            return flag;
        } catch (UnsupportedEncodingException e) {
            log.info("判断源数据与加密数据错误：",e);
            throw new IllegalStateException("判断源数据与加密数据异常");
        }

    }

}
