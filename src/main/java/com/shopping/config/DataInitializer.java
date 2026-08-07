package com.shopping.config;

import com.shopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** 启动时修正演示账号密码（admin/test 密码均为 123456） */
@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        userService.resetDemoPasswordIfNeeded();
        log.info("shopping2 启动完成，演示账号 admin/123456（管理员）、test/123456（普通用户）");
    }
}
