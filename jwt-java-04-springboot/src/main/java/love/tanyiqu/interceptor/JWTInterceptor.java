package love.tanyiqu.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import love.tanyiqu.util.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            // 获取Headers中的token
            String token = request.getHeader("token");
            JWTUtil.verifyToken(token);
            // 验证成功，放行
            return true;
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("status", 401);
            map.put("msg", "权限认证失败");

            // 验证失败，提示信息
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(json);
            return false;
        }
    }
}
