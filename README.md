# shopping2 电子商务网站

网络应用开发课程项目（电子商务网站）。原版为 Java 7 + Servlet + JSP + MySQL，
本仓库 `refactor` 分支完成现代化重构：

- **Spring Boot 3.3 + Java 17 + MyBatis**（原 Servlet/JSP + JDBC 手写 DAO）
- **Thymeleaf + Bootstrap 5** 页面（原 JSP + 手写 CSS）
- **H2 数据库开箱即用**（兼容 MySQL 模式，无需安装 MySQL；也可切换 MySQL）
- **REST API** 统一业务入口，页面交互与 API 验证共用同一套 Service
- 密码 BCrypt 加密存储（原明文存储）
- 分层：controller / service / mapper / entity / dto，SLF4J 日志替代 System.out

## 快速开始

需要 JDK 17+ 与 Maven：

```bash
mvn package
java -Dfile.encoding=UTF-8 -jar target/shopping2-2.0.0.jar
```

访问 http://localhost:8080

## 演示账号

| 账号 | 密码 | 角色 |
| --- | --- | --- |
| `admin` | `123456` | 管理员（商品管理、销售报表、客户日志） |
| `test` | `123456` | 普通用户（浏览、加购、结算） |

## 功能清单

- 用户注册/登录/注销（BCrypt 密码、用户名唯一）
- 商品浏览与搜索（名称模糊查询）
- 购物车：加购自动累加、数量增减、移出、结算总价、购买
- 管理员：商品添加/修改/删除、销售统计报表（按商品合并销量）、客户购买/浏览日志（可按用户查询）

## REST API

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| POST | /api/auth/register | 注册 |
| POST | /api/auth/login | 登录（Session 保持） |
| POST | /api/auth/logout | 注销 |
| GET | /api/auth/me | 当前用户 |
| GET | /api/products | 商品列表（?keyword= 搜索） |
| POST/PUT/DELETE | /api/products... | 商品管理（管理员） |
| GET | /api/cart | 我的购物车 |
| POST | /api/cart/add?productId= | 加购 |
| POST | /api/cart/{id}/increase / decrease | 数量增减 |
| DELETE | /api/cart/{id} | 移出 |
| GET | /api/cart/settlement | 结算数据（列表+总价） |
| POST | /api/cart/pay | 购买完成 |
| GET | /api/admin/sales | 销售报表（管理员） |
| GET | /api/admin/logs?userId= | 客户日志（管理员） |

## 切换 MySQL

修改 `application.yml` 数据源为 MySQL 连接信息即可（`mysql-connector-j` 已包含）。
