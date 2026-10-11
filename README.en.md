[简体中文](README.md) | English

# shopping2 · E-Commerce Platform

**A lightweight full-stack e-commerce web application modernized with Spring Boot 3.3 and Java 17.**

`shopping2` is a complete B2C e-commerce platform refactored from legacy Java 7 / Servlet / JSP foundations onto **Spring Boot 3.3, Java 17, and MyBatis**. Featuring a responsive Thymeleaf and Bootstrap 5 frontend alongside an embedded H2 database (MySQL compatibility mode), it launches with zero external database prerequisites while providing clean RESTful endpoints and administrator telemetry dashboards.

---

## Architectural Modernization Highlights

* **Zero-Dependency Out-of-the-Box**: Embeds an in-memory H2 database with automatic schema bootstrapping and mock seed data, eliminating the need to install or configure external MySQL services during testing; seamlessly toggles back to physical MySQL via configuration.
* **Standard Multi-Tier Architecture**: Organizes logic across standard `Controller → Service → Mapper → Entity / DTO` boundaries, using MyBatis for type-safe queries and SLF4J / Logback for structured logging.
* **Security & Credential Hashing**: Migrated from legacy plaintext credentials to BCrypt one-way password hashing.
* **Full-Cycle E-Commerce Flows**:
  * **User Authentication**: Account registration, session login, and profile tracking;
  * **Product Discovery**: Multi-keyword fuzzy search and categorized catalog navigation;
  * **Shopping Cart & Checkout**: Automatic line-item merging, quantity adjustments, dynamic totals, and checkout settlements;
  * **Admin Operations**: Product CRUD, aggregated sales volume reports, and customer audit access logs.

---

## Technical Stack

| Layer | Technology | Role |
| :--- | :--- | :--- |
| **Framework** | Spring Boot 3.3.x (Java 17) | Core backend ecosystem |
| **Persistence** | MyBatis 3 + H2 Database | Embedded database with zero external setup |
| **View Layer** | Thymeleaf + Bootstrap 5 | Server-side rendered responsive layouts |
| **Security & Utilities** | BCrypt + Lombok + SLF4J | Cryptographic hashing and logging |

---

## Quick Start

### Prerequisites
* **Java**: JDK 17 or newer
* **Build Tool**: Apache Maven 3.8+

### Build & Run

```bash
# 1. Package executable JAR
mvn package

# 2. Run application
java -Dfile.encoding=UTF-8 -jar target/shopping2-2.0.0.jar
```

Once running, navigate to `http://localhost:8080` in your browser.

### Seed Accounts

| Username | Password | Role | Permissions |
| :--- | :--- | :--- | :--- |
| `admin` | `123456` | Administrator | Product management, sales reports, audit logs |
| `test` | `123456` | Customer | Catalog browsing, cart management, checkout |

---

## Core REST Endpoints

```text
POST   /api/auth/login             # User authentication
GET    /api/products?keyword=      # Product search and listing
POST   /api/cart/add?productId=    # Add item to cart
GET    /api/cart/settlement        # Cart order totals calculation
POST   /api/cart/pay               # Confirm checkout
GET    /api/admin/sales            # Sales volume report (admin only)
GET    /api/admin/logs?userId=     # Customer audit log (admin only)
```

---

## License

This project is open-sourced under the [MIT License](LICENSE).
