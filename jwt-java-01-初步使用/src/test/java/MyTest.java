import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;

public class MyTest {


    // 生成
    @Test
    public void create() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 5);

        String token = JWT.create()
                .withClaim("userId", 1)
                .withClaim("username", "tanyiqu")
                .withExpiresAt(instance.getTime())    // 指定过期时间
                .sign(Algorithm.HMAC256("!@#$%^&*()-="));

        System.out.println(token);
    }

    // 验证
    @Test
    public void require() {
        @SuppressWarnings("SpellCheckingInspection")
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTA5NTYwMDcsInVzZXJJZCI6MSwidXNlcm5hbWUiOiJ0YW55aXF1In0.owdVbGadDS1iF88db8M7wcCBGFfokCaTZFpRveEU0Fc";

        // 生成验证对象
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("!@#$%^&*()-=")).build();

        // 验证
        DecodedJWT decodedJWT = verifier.verify(token);

        Claim userId = decodedJWT.getClaim("userId");
        Claim username = decodedJWT.getClaim("username");

        System.out.println(userId.asInt());
        System.out.println(username.asString());
        System.out.println(decodedJWT.getExpiresAt());
    }

}
