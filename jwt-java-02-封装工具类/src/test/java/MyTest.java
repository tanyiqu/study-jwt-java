import com.auth0.jwt.interfaces.DecodedJWT;
import love.tanyiqu.util.JWTUtil;
import org.junit.Test;

import java.util.HashMap;

public class MyTest {

    @Test
    public void issue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");

        System.out.println(JWTUtil.issueToken(map));
    }

    @Test
    public void verify() {
        @SuppressWarnings("SpellCheckingInspection")
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJleHAiOjE2MTE1NjM4MjZ9.P90kCiTh833PJJ_ZKfPuOsTVkHOF2cYu3itMZnNgpt0";

        try {
            DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
            String id = decodedJWT.getClaim("id").asString();
            System.out.println(id);
        } catch (Exception e) {
            System.out.println("token 无效");
        }
    }
}
