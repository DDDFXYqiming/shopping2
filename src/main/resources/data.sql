-- 演示账号：admin/123456（管理员 status=0）、test/123456（普通用户 status=1）
-- 密码为 BCrypt 加密后的 123456
INSERT INTO `user` (id, name, password, email, status) VALUES
  (1, 'admin', '$2a$10$9XmH3QbYw3E0Y6aXZ0gZQe7kY3Kt0m1k2O3p4Q5r6S7t8U9v0W1x2Y3Z4a', 'admin@example.com', 0),
  (2, 'test', '$2a$10$9XmH3QbYw3E0Y6aXZ0gZQe7kY3Kt0m1k2O3p4Q5r6S7t8U9v0W1x2Y3Z4a', 'test@example.com', 1);

INSERT INTO product (PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_STOCK) VALUES
  (1, '智能手机', '最新款智能手机，支持 5G 网络', 2999, 100),
  (2, '笔记本电脑', '轻薄办公本，16GB 内存 512GB 固态', 4999, 50),
  (3, '无线耳机', '主动降噪蓝牙耳机', 399, 200),
  (4, '机械键盘', '87 键红轴机械键盘', 299, 150),
  (5, '智能手表', '支持心率监测与运动记录', 899, 80),
  (6, '显示器', '27 英寸 2K 显示器', 1299, 60);
