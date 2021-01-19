package love.tanyiqu.controller;

import love.tanyiqu.pojo.User;
import love.tanyiqu.service.UserService;
import love.tanyiqu.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public Map<String, Object> login(String username, String pwd) {
        HashMap<String, Object> map = new HashMap<>();
//        if (username == null || pwd == null) {
//            map.put("status", 401);
//            map.put("msg", "请传入必要参数");
//            return map;
//        }

        try {
            // 数据库验证
            User user = userService.login(username, pwd);

            // 生成token
            HashMap<String, String> payload = new HashMap<>();
            payload.put("username", user.getUsername());
            String token = JWTUtil.issueToken(payload);

            map.put("token", token);
        } catch (Exception e) {
            map.put("status", 401);
            map.put("msg", e.getMessage());
            return map;
        }
        map.put("status", 200);
        map.put("msg", "登录成功");
        return map;
    }

    // 其他接口，使用token验证权限
    @RequestMapping("/user/test")
    public Map<String, Object> test(String token) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            JWTUtil.verifyToken(token);
            map.put("status", 200);
            map.put("msg", "认证成功");
        } catch (Exception e) {
            map.put("status", 401);
            map.put("msg", "认证失败");
        }

        return map;
    }

}
