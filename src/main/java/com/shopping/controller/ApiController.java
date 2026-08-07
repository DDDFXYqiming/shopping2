package com.shopping.controller;

import com.shopping.dto.ApiResponse;
import com.shopping.entity.Cart;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.service.CartService;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;
import com.shopping.util.SessionKeys;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** REST API：验收与页面交互共用同一套业务接口 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    public ApiController(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    // ---------- 认证 ----------

    @PostMapping("/auth/register")
    public ApiResponse<User> register(@RequestParam String name,
                                      @RequestParam String password,
                                      @RequestParam(required = false) String email) {
        try {
            User user = userService.register(name, password, email);
            return ApiResponse.ok(user, "用户注册成功！请登录。");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public ApiResponse<User> login(@RequestParam String username,
                                   @RequestParam String password,
                                   HttpSession session) {
        User user = userService.login(username, password);
        if (user == null) {
            return ApiResponse.fail("用户登录失败！请重新登录");
        }
        SessionKeys.login(session, user);
        return ApiResponse.ok(user, "登录成功");
    }

    @PostMapping("/auth/logout")
    public ApiResponse<Void> logout(HttpSession session) {
        SessionKeys.logout(session);
        log.info("用户退出登录");
        return ApiResponse.ok(null, "退出成功");
    }

    @GetMapping("/auth/me")
    public ApiResponse<User> me(HttpSession session) {
        User user = SessionKeys.currentUser(session);
        if (user == null) {
            return ApiResponse.fail("未登录");
        }
        return ApiResponse.ok(user, "ok");
    }

    // ---------- 商品 ----------

    @GetMapping("/products")
    public ApiResponse<List<Product>> products(@RequestParam(required = false) String keyword) {
        return ApiResponse.ok(productService.search(keyword), "ok");
    }

    @GetMapping("/products/{id}")
    public ApiResponse<Product> product(@PathVariable int id) {
        return ApiResponse.ok(productService.get(id), "ok");
    }

    @PostMapping("/products")
    public ApiResponse<Product> addProduct(@RequestParam String PRODUCT_NAME,
                                           @RequestParam String PRODUCT_DESCRIPTION,
                                           @RequestParam int PRODUCT_PRICE,
                                           @RequestParam int PRODUCT_STOCK,
                                           HttpSession session) {
        if (!SessionKeys.isAdmin(session)) {
            return ApiResponse.fail("无权限：仅管理员可添加商品");
        }
        try {
            return ApiResponse.ok(productService.add(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_STOCK), "添加成功");
        } catch (IllegalArgumentException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/products/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable int id,
                                              @RequestParam String PRODUCT_NAME,
                                              @RequestParam String PRODUCT_DESCRIPTION,
                                              @RequestParam int PRODUCT_PRICE,
                                              @RequestParam int PRODUCT_STOCK,
                                              HttpSession session) {
        if (!SessionKeys.isAdmin(session)) {
            return ApiResponse.fail("无权限：仅管理员可修改商品");
        }
        try {
            return ApiResponse.ok(productService.update(id, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_STOCK), "修改成功");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @DeleteMapping("/products/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable int id, HttpSession session) {
        if (!SessionKeys.isAdmin(session)) {
            return ApiResponse.fail("无权限：仅管理员可删除商品");
        }
        productService.delete(id);
        return ApiResponse.ok(null, "删除成功");
    }

    // ---------- 购物车 ----------

    @GetMapping("/cart")
    public ApiResponse<List<Cart>> cart(HttpSession session) {
        User user = requireLogin(session);
        if (user == null) return ApiResponse.fail("请先登录");
        return ApiResponse.ok(cartService.list(user.getId()), "ok");
    }

    @PostMapping("/cart/add")
    public ApiResponse<Void> addToCart(@RequestParam int productId, HttpSession session) {
        User user = requireLogin(session);
        if (user == null) return ApiResponse.fail("请先登录后，再购买");
        try {
            cartService.add(user.getId(), productId);
            return ApiResponse.ok(null, "已加入购物车");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/cart/{cartId}/increase")
    public ApiResponse<Void> increase(@PathVariable int cartId, HttpSession session) {
        User user = requireLogin(session);
        if (user == null) return ApiResponse.fail("请先登录");
        try {
            cartService.increase(user.getId(), cartId);
            return ApiResponse.ok(null, "ok");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/cart/{cartId}/decrease")
    public ApiResponse<Void> decrease(@PathVariable int cartId, HttpSession session) {
        User user = requireLogin(session);
        if (user == null) return ApiResponse.fail("请先登录");
        try {
            cartService.decrease(user.getId(), cartId);
            return ApiResponse.ok(null, "ok");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @DeleteMapping("/cart/{cartId}")
    public ApiResponse<Void> removeFromCart(@PathVariable int cartId, HttpSession session) {
        User user = requireLogin(session);
        if (user == null) return ApiResponse.fail("请先登录");
        try {
            cartService.remove(user.getId(), cartId);
            return ApiResponse.ok(null, "已移出购物车");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/cart/settlement")
    public ApiResponse<Map<String, Object>> settlement(HttpSession session) {
        User user = requireLogin(session);
        if (user == null) return ApiResponse.fail("请先登录");
        return ApiResponse.ok(cartService.settlement(user.getId()), "ok");
    }

    @PostMapping("/cart/pay")
    public ApiResponse<Void> pay(HttpSession session) {
        User user = requireLogin(session);
        if (user == null) return ApiResponse.fail("请先登录");
        cartService.pay(user.getId());
        return ApiResponse.ok(null, "购买成功！已发送邮件");
    }

    // ---------- 管理员 ----------

    @GetMapping("/admin/sales")
    public ApiResponse<List<Cart>> sales(HttpSession session) {
        if (!SessionKeys.isAdmin(session)) return ApiResponse.fail("无权限：仅管理员可查看");
        return ApiResponse.ok(cartService.salesReport(), "ok");
    }

    @GetMapping("/admin/logs")
    public ApiResponse<Map<String, Object>> logs(@RequestParam(required = false) Integer userId, HttpSession session) {
        if (!SessionKeys.isAdmin(session)) return ApiResponse.fail("无权限：仅管理员可查看");
        return ApiResponse.ok(cartService.logs(userId), "ok");
    }

    private User requireLogin(HttpSession session) {
        return SessionKeys.currentUser(session);
    }
}
