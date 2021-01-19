package love.tanyiqu.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author tanyiqu
 * @date 2021/1/18
 */
@SuppressWarnings("unused")
public class JWTUtil {

    // 签名
    private static final String SIGN = "!@#$%^&*()_+987tanyiqu";


    /**
     * 生成token
     *
     * @param payload JWT的payload
     * @return token
     */
    public static String issueToken(Map<String, String> payload) {
        JWTCreator.Builder builder = JWT.create();

        // 默认7天过期
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);
        builder.withExpiresAt(instance.getTime());

        payload.forEach((k, v) -> {
            //noinspection Convert2MethodRef
            builder.withClaim(k, v);
        });

        return builder.sign(Algorithm.HMAC256(SIGN));
    }


    /**
     * 验证token
     *
     * @param token token
     * @return DecodedJWT
     */
    public static DecodedJWT verifyToken(String token) {
        // 生成验证对象
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SIGN)).build();
        // 验证
        return verifier.verify(token);
    }

}
