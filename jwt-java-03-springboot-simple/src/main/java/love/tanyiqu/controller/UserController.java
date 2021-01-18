package love.tanyiqu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/login")
    public String login(String username){
        if (username != null){
            return "名字为：" + username;
        }
        return "没有名字";
    }

}
