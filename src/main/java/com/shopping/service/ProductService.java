package com.shopping.service;

import com.shopping.entity.Product;
import com.shopping.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> list() {
        return productMapper.selectAll();
    }

    public List<Product> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return productMapper.selectAll();
        }
        return productMapper.selectByNameLike(keyword.trim());
    }

    public Product get(int id) {
        return productMapper.selectById(id);
    }

    public Product add(String name, String description, int price, int stock) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("商品名称不能为空");
        }
        Product product = new Product();
        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductPrice(price);
        product.setProductStock(stock);
        productMapper.insert(product);
        log.info("新增商品 id={}, name={}, price={}, stock={}", product.getProductId(), name, price, stock);
        return product;
    }

    public Product update(int id, String name, String description, int price, int stock) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new IllegalStateException("商品不存在");
        }
        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductPrice(price);
        product.setProductStock(stock);
        productMapper.update(product);
        log.info("修改商品 id={}, name={}, price={}, stock={}", id, name, price, stock);
        return product;
    }

    public void delete(int id) {
        productMapper.deleteById(id);
        log.info("删除商品 id={}", id);
    }
}
