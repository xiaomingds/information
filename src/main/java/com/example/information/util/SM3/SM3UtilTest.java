package com.example.information.util.SM3;

/**
 * @ClassName SM3UtilTest
 * @Author xiaomingds
 * @Date 2021/4/20 19:35
 **/

import lombok.extern.slf4j.Slf4j;

/**
 * @author FYF
 * @date Created in 15:27 2020/6/4
 */
@Slf4j
public class SM3UtilTest {

    public static final String PWD_LENGTH_EX_MSG = "密码的长度必须等于6";


    public static String ToCode(String password) {
        Integer pwdLength=6;
       Integer pwdSize=password.length();
       String pwdEncryptAssembly=SM3Util.pwdEncryptAssembly(password);
        String  pwdEncrypt = "";
        if (pwdSize >= pwdLength) {
            log.info("您输入的密码:{}", password);
            log.info("您密码加密组合并组合:{}", pwdEncryptAssembly);
             pwdEncrypt = SM3Util.pwdEncrypt(password);
            log.info("最终组合密码结果:{}", pwdEncrypt);

        } else {
            log.info("错误信息:{}", PWD_LENGTH_EX_MSG);
        }
        return pwdEncrypt;
    }

}
