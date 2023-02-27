package entity;

public class cart {
    private int cart_id; //购物车id
    private int cart_product_id; //购物车商品id
    private String cart_product_name; //购物车中的商品名称
    private int cart_product_price; //购物车商品价格
    private int cart_product_nums; //购物车商品数量
    private int cart_product_stock; //购物车商品库存
    private int cart_user_id; //购物车用户id
    private int cart_valid; //购物车状态判断
    private String created_at;
    private String updated_at;

    public cart(){}

    public cart(int cart_id,  String cart_product_name,int cart_product_price, int cart_product_nums, int cart_product_stock, int cart_product_id,int cart_user_id, int cart_valid,String created_at,String updated_at){
        this.cart_id = cart_id;
        this.cart_product_id = cart_product_id;
        this.cart_product_name = cart_product_name;
        this.cart_product_price = cart_product_price;
        this.cart_product_nums = cart_product_nums;
        this.cart_product_stock = cart_product_stock;
        this.cart_user_id = cart_user_id;
        this.cart_valid = cart_valid;
        this.created_at =created_at;
        this.updated_at=updated_at;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_product_id() {
        return cart_product_id;
    }

    public void setCart_product_id(int cart_product_id) {
        this.cart_product_id = cart_product_id;
    }

    public String getCart_product_name() {
        return cart_product_name;
    }

    public void setCart_product_name(String cart_product_name) {
        this.cart_product_name = cart_product_name;
    }

    public int getCart_product_price() {
        return cart_product_price;
    }

    public void setCart_product_price(int cart_product_price) {
        this.cart_product_price = cart_product_price;
    }

    public int getCart_product_nums() {
        return cart_product_nums;
    }

    public void setCart_product_nums(int cart_product_nums) {
        this.cart_product_nums = cart_product_nums;
    }

    public int getCart_product_stock() {
        return cart_product_stock;
    }

    public void setCart_product_stock(int cart_product_stock) {
        this.cart_product_stock = cart_product_stock;
    }

    public int getCart_user_id() {
        return cart_user_id;
    }

    public void setCart_user_id(int cart_user_id) {this.cart_user_id = cart_user_id;}

    public int getCart_valid() {
        return cart_valid;
    }

    public void setCart_valid(int cart_valid) {
        this.cart_valid = cart_valid;
    }

    public String getCart_created_at() {
        return created_at;
    }

    public String getCart_updated_at() {
        return updated_at;
    }

}
