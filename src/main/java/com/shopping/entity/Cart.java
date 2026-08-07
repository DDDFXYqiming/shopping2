package com.shopping.entity;

/** 购物车实体（cart 表） */
public class Cart {
    private int cartId;
    private int cartProductId;
    private String cartProductName;
    private int cartProductPrice;
    private int cartProductNums;
    private int cartProductStock;
    private int cartUserId;
    /** 1=购物车中（未购买），0=已购买 */
    private int cartValid;
    private String createdAt;
    private String updatedAt;

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getCartProductId() { return cartProductId; }
    public void setCartProductId(int cartProductId) { this.cartProductId = cartProductId; }
    public String getCartProductName() { return cartProductName; }
    public void setCartProductName(String cartProductName) { this.cartProductName = cartProductName; }
    public int getCartProductPrice() { return cartProductPrice; }
    public void setCartProductPrice(int cartProductPrice) { this.cartProductPrice = cartProductPrice; }
    public int getCartProductNums() { return cartProductNums; }
    public void setCartProductNums(int cartProductNums) { this.cartProductNums = cartProductNums; }
    public int getCartProductStock() { return cartProductStock; }
    public void setCartProductStock(int cartProductStock) { this.cartProductStock = cartProductStock; }
    public int getCartUserId() { return cartUserId; }
    public void setCartUserId(int cartUserId) { this.cartUserId = cartUserId; }
    public int getCartValid() { return cartValid; }
    public void setCartValid(int cartValid) { this.cartValid = cartValid; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
