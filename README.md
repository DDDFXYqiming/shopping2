简体中文 | [English](README.en.md)

# shopping2 · 电子商务应用系统

**基于 Spring Boot 3.3 与 Java 17 现代化重构的轻量级全栈电商系统**。

`shopping2` 是一个功能完备的 B2C 电子商务商城应用系统。工程从传统的 Java 7 / Servlet / JSP 架构全面现代化升级重构至 **Spring Boot 3.3 + Java 17 + MyBatis**，前端采用 Thymeleaf 模板引擎与 Bootstrap 5 响应式框架，内置 H2 内存数据库（MySQL 语法兼容模式），实现克隆后零外部中间件依赖、开箱即用一键启动，并提供统一的 RESTful API 与管理员数据报表。

---

## 核心特性与架构升级

* **开箱即用零依赖（Zero-Dependency Out-of-the-Box）**：内置 H2 内存数据库（配置 MySQL 兼容语法），启动即可自动完成 DDL 建表与初始种子数据填充，无需预先安装或配置 MySQL 服务；修改配置即可无缝切回生产 MySQL。
* **分层架构与现代化工程规范**：严格遵循 `Controller → Service → Mapper → Entity / DTO` 标准分层规范，采用 MyBatis 进行类型安全持久化映射，SLF4J + Logback 替换原始控制台打印。
* **安全审计与凭据加密**：全量集成 Spring Security 推荐的 BCrypt 密码单向哈希加密存储，杜绝明文凭据泄露风险。
* **全生命周期电商业务链路**：
  * **用户体系**：注册、登录、Session 状态维护与个人凭据管理；
  * **商品流**：多条件模糊搜索、分类检索与详情透视；
  * **购物车与结算流**：加购自动合并累加、动态数量增减、实时总价与阶梯运费结算、模拟支付结案；
  * **管理运营大盘**：商品发布与上下架（CRUD）、销量聚合统计报表（按商品归并汇总）、客户行为与购买审计日志。

---

## 技术架构

| 分层 | 技术选型 | 说明 |
| :--- | :--- | :--- |
| **核心框架** | Spring Boot 3.3.x (Java 17) | 现代企业级 Spring 生态底座 |
| **持久层** | MyBatis 3 + H2 Database | 默认内置 H2 内存库，支持一行配置平滑切换 MySQL |
| **视图层** | Thymeleaf + Bootstrap 5 | 现代化响应式服务端渲染页面 |
| **安全与工具** | BCrypt + Lombok + SLF4J | 凭据哈希加密与现代化开发体验 |

---

## 快速开始

### 环境依赖
* **Java**：JDK 17 或更高版本
* **构建工具**：Apache Maven 3.8+

### 编译与启动

```bash
# 1. 编译打包生成可执行 JAR
mvn package

# 2. 运行应用
java -Dfile.encoding=UTF-8 -jar target/shopping2-2.0.0.jar
```

服务启动后，在浏览器中访问：`http://localhost:8080`。

### 演示账号

| 账号 | 密码 | 角色 | 权限说明 |
| :--- | :--- | :--- | :--- |
| `admin` | `123456` | 系统管理员 | 商品全量 CRUD、销售数据统计报表、操作审计日志 |
| `test` | `123456` | 普通消费者 | 商品浏览、加购、购物车编辑、结算支付 |

---

## REST API 核心端点

系统不仅提供 Thymeleaf 网页界面，同时对外暴露标准 RESTful API：

```text
POST   /api/auth/login             # 用户登录认证
GET    /api/products?keyword=      # 商品模糊查询与列表
POST   /api/cart/add?productId=    # 商品加入购物车
GET    /api/cart/settlement        # 购物车结算汇总
POST   /api/cart/pay               # 确认支付结单
GET    /api/admin/sales            # 销售大盘报表（管理员）
GET    /api/admin/logs?userId=     # 客户行为日志（管理员）
```

---

## 许可证

本项目基于 [MIT License](LICENSE) 开源。
