package com.example.information.util.Jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.information.entity.User;

import java.util.Date;

/**
 * @author Zeng
 * @date 2020/2/17 10:56
 */
public class TokenUtil {
    private static final long EXPIRE_TIME= 60*60*1000;//单位是毫秒
    private static final String TOKEN_SECRET="token123";  //密钥盐
    public static final String TOKEN_LOGIN_NAME = "userName";


    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){

        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userName", user.getUserName())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;

    }


    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){


        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);

            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }

    } public static String verityName(String token){
            String result = "";
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            result += jwt.getClaims().get(TOKEN_LOGIN_NAME).asString();
            return result; // success:username

    }

}
