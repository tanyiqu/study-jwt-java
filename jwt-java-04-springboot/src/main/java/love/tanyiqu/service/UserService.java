package love.tanyiqu.service;

import love.tanyiqu.pojo.User;
import org.springframework.stereotype.Service;

/**
 * 不连接数据库，用特定的用户名和密码代替验证
 */
@Service
public class UserService {

    // 根据接收的用户名和密码，查询用户是否存在
    public User login(String username, String pwd) {

        User user = new User(username, pwd);

        // 简单判断
        boolean exist = userExist(user);

        if (exist) {
            return user;
        } else {
            throw new RuntimeException("账号名或密码错误");
        }

    }

    private boolean userExist(User user) {
        return "root".equals(user.getUsername()) && "123".equals(user.getPassword());
    }

}
