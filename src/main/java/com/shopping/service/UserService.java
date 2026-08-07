package com.shopping.service;

import com.shopping.entity.User;
import com.shopping.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /** 注册：用户名唯一，密码 BCrypt 加密存储 */
    public User register(String name, String password, String email) {
        if (name == null || name.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("用户名和密码不能为空");
        }
        if (userMapper.selectByName(name) != null) {
            throw new IllegalStateException("用户名已存在");
        }
        User user = new User();
        user.setName(name);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setStatus(1);
        userMapper.insert(user);
        log.info("用户注册成功 id={}, name={}", user.getId(), name);
        return user;
    }

    /** 登录：校验密码 */
    public User login(String name, String password) {
        User user = userMapper.selectByName(name);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            log.warn("登录失败：用户名或密码错误 name={}", name);
            return null;
        }
        log.info("用户登录成功 id={}, name={}, status={}", user.getId(), name, user.getStatus());
        return user;
    }

    public User getById(int id) {
        return userMapper.selectById(id);
    }

    /** 修正演示账号密码（保证 admin/test 的 123456 始终可用） */
    public void resetDemoPasswordIfNeeded() {
        for (String name : new String[]{"admin", "test"}) {
            User user = userMapper.selectByName(name);
            if (user != null) {
                String encoded = encoder.encode("123456");
                userMapper.updatePasswordByName(name, encoded);
                log.info("演示账号密码已初始化 name={}", name);
            }
        }
    }
}
