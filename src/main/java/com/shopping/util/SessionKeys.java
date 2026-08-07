package com.shopping.util;

import com.shopping.entity.User;
import jakarta.servlet.http.HttpSession;

/** 会话工具：保持与原版 session 契约（user / islogin） */
public final class SessionKeys {
    public static final String USER = "user";
    public static final String IS_LOGIN = "islogin";

    private SessionKeys() {}

    public static boolean isLogin(HttpSession session) {
        return session != null && "1".equals(session.getAttribute(IS_LOGIN));
    }

    public static User currentUser(HttpSession session) {
        if (session == null) return null;
        Object user = session.getAttribute(USER);
        return user instanceof User ? (User) user : null;
    }

    public static boolean isAdmin(HttpSession session) {
        User user = currentUser(session);
        return user != null && user.getStatus() == 0;
    }

    public static void login(HttpSession session, User user) {
        session.setAttribute(USER, user);
        session.setAttribute(IS_LOGIN, "1");
    }

    public static void logout(HttpSession session) {
        if (session != null) {
            session.removeAttribute(USER);
            session.setAttribute(IS_LOGIN, "0");
        }
    }
}
