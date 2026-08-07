package com.shopping.controller;

import com.shopping.entity.User;
import com.shopping.service.CartService;
import com.shopping.service.ProductService;
import com.shopping.util.SessionKeys;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/** Thymeleaf 页面路由：页面数据由服务端渲染，交互操作走 REST API */
@Controller
public class PageController {
    private final ProductService productService;
    private final CartService cartService;

    public PageController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    private void fillSessionModel(HttpSession session, Model model) {
        User user = SessionKeys.currentUser(session);
        model.addAttribute("isLogin", SessionKeys.isLogin(session));
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", SessionKeys.isAdmin(session));
    }

    @GetMapping({"/login", "/login.html"})
    public String login(HttpSession session, Model model) {
        fillSessionModel(session, model);
        return "login";
    }

    @GetMapping({"/register", "/register.html"})
    public String register(HttpSession session, Model model) {
        fillSessionModel(session, model);
        return "register";
    }

    @GetMapping({"/", "/index.html"})
    public String index(@RequestParam(required = false) String keyword, HttpSession session, Model model) {
        fillSessionModel(session, model);
        model.addAttribute("products", productService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        fillSessionModel(session, model);
        User user = SessionKeys.currentUser(session);
        if (user != null) {
            model.addAttribute("cartList", cartService.list(user.getId()));
        }
        return "cart";
    }

    @GetMapping("/cart/settlement")
    public String settlement(HttpSession session, Model model) {
        fillSessionModel(session, model);
        User user = SessionKeys.currentUser(session);
        if (user != null) {
            Map<String, Object> data = cartService.settlement(user.getId());
            model.addAttribute("cartList", data.get("cartList"));
            model.addAttribute("totalPrice", data.get("totalPrice"));
        }
        return "settlement";
    }

    @GetMapping("/product/add")
    public String addProduct(HttpSession session, Model model) {
        fillSessionModel(session, model);
        return "product-add";
    }

    @GetMapping("/product/modify")
    public String modifyProduct(@RequestParam int id, HttpSession session, Model model) {
        fillSessionModel(session, model);
        model.addAttribute("product", productService.get(id));
        return "product-modify";
    }

    @GetMapping("/admin/sales")
    public String sales(HttpSession session, Model model) {
        fillSessionModel(session, model);
        if (SessionKeys.isAdmin(session)) {
            model.addAttribute("sales", cartService.salesReport());
        }
        return "sales";
    }

    @GetMapping("/admin/logs")
    public String logs(HttpSession session, Model model) {
        fillSessionModel(session, model);
        if (SessionKeys.isAdmin(session)) {
            model.addAttribute("logs", cartService.logs(null));
        }
        return "logs";
    }
}
