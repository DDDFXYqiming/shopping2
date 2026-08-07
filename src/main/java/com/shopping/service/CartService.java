package com.shopping.service;

import com.shopping.entity.Cart;
import com.shopping.entity.Product;
import com.shopping.mapper.CartMapper;
import com.shopping.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public CartService(CartMapper cartMapper, ProductMapper productMapper) {
        this.cartMapper = cartMapper;
        this.productMapper = productMapper;
    }

    /** 当前用户购物车（过滤掉商品已不存在的条目） */
    public List<Cart> list(int userId) {
        List<Cart> list = cartMapper.selectValidByUserId(userId);
        List<Cart> valid = new ArrayList<>();
        for (Cart cart : list) {
            if (productMapper.selectById(cart.getCartProductId()) != null) {
                valid.add(cart);
            } else {
                log.warn("购物车条目对应商品已删除，清理 cartId={}", cart.getCartId());
                cartMapper.deleteById(cart.getCartId());
            }
        }
        return valid;
    }

    /** 加入购物车：已存在则数量 +1，否则新增；校验库存 */
    public void add(int userId, int productId) {
        Product product = productMapper.selectById(productId);
        if (product == null) {
            throw new IllegalStateException("商品不存在");
        }
        Cart existing = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (existing != null) {
            if (existing.getCartProductNums() + 1 > product.getProductStock()) {
                throw new IllegalStateException("库存不足，无法继续添加");
            }
            cartMapper.updateNums(existing.getCartId(), existing.getCartProductNums() + 1);
            log.info("购物车数量 +1 userId={}, cartId={}, nums={}", userId, existing.getCartId(), existing.getCartProductNums() + 1);
        } else {
            if (product.getProductStock() <= 0) {
                throw new IllegalStateException("库存不足，无法购买");
            }
            Cart cart = new Cart();
            cart.setCartProductId(product.getProductId());
            cart.setCartProductName(product.getProductName());
            cart.setCartProductPrice(product.getProductPrice());
            cart.setCartProductNums(1);
            cart.setCartProductStock(product.getProductStock());
            cart.setCartUserId(userId);
            cartMapper.insert(cart);
            log.info("加入购物车 userId={}, productId={}", userId, productId);
        }
    }

    /** 数量 +1 */
    public void increase(int userId, int cartId) {
        Cart cart = ownedCart(userId, cartId);
        cartMapper.updateNums(cartId, cart.getCartProductNums() + 1);
        log.info("购物车数量 +1 userId={}, cartId={}", userId, cartId);
    }

    /** 数量 -1；减到 0 时移除条目 */
    public void decrease(int userId, int cartId) {
        Cart cart = ownedCart(userId, cartId);
        if (cart.getCartProductNums() - 1 <= 0) {
            cartMapper.deleteById(cartId);
            log.info("购物车条目移除 userId={}, cartId={}", userId, cartId);
        } else {
            cartMapper.updateNums(cartId, cart.getCartProductNums() - 1);
            log.info("购物车数量 -1 userId={}, cartId={}", userId, cartId);
        }
    }

    /** 移除整个条目 */
    public void remove(int userId, int cartId) {
        ownedCart(userId, cartId);
        cartMapper.deleteById(cartId);
        log.info("购物车条目删除 userId={}, cartId={}", userId, cartId);
    }

    /** 结算数据：购物车列表 + 总价 */
    public Map<String, Object> settlement(int userId) {
        List<Cart> list = list(userId);
        int totalPrice = list.stream()
                .mapToInt(c -> c.getCartProductPrice() * c.getCartProductNums())
                .sum();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("cartList", list);
        result.put("totalPrice", totalPrice);
        return result;
    }

    /** 购买完成：购物车全部置为已购买（cart_valid=0） */
    public void pay(int userId) {
        List<Cart> list = list(userId);
        for (Cart cart : list) {
            cartMapper.updateValidToZero(cart.getCartId());
        }
        log.info("用户购买完成 userId={}, items={}", userId, list.size());
    }

    /** 销售报表：已售记录按商品合并数量 */
    public List<Cart> salesReport() {
        List<Cart> purchased = cartMapper.selectAllPurchased();
        Map<Integer, Cart> merged = new LinkedHashMap<>();
        for (Cart cart : purchased) {
            Cart old = merged.get(cart.getCartProductId());
            if (old == null) {
                merged.put(cart.getCartProductId(), cart);
            } else {
                old.setCartProductNums(old.getCartProductNums() + cart.getCartProductNums());
            }
        }
        return new ArrayList<>(merged.values());
    }

    /** 客户日志：全部购买/浏览记录，可按用户筛选 */
    public Map<String, Object> logs(Integer userId) {
        List<Cart> purchased = userId == null
                ? cartMapper.selectAllPurchased()
                : cartMapper.selectPurchasedByUserId(userId);
        List<Cart> browsing = cartMapper.selectAll();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("purchased", purchased);
        result.put("browsing", browsing);
        return result;
    }

    private Cart ownedCart(int userId, int cartId) {
        Cart cart = cartMapper.selectByIdAndUserId(cartId, userId);
        if (cart == null) {
            throw new IllegalStateException("购物车条目不存在");
        }
        return cart;
    }
}
