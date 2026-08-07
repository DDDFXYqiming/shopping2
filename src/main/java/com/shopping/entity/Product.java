package com.shopping.entity;

/** 商品实体（product 表，列名保持原数据库大写风格） */
public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productStock;

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }
    public int getProductStock() { return productStock; }
    public void setProductStock(int productStock) { this.productStock = productStock; }
}
