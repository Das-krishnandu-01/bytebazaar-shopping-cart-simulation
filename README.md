# 🛒 ByteBazaar - Online Shopping Cart Simulation

ByteBazaar is a Java-based console application that simulates the core functionality of an e-commerce shopping cart system. It allows users to view products, manage a cart, and place orders with database integration using MySQL and JDBC.

---

## 🚀 Features

- 📦 View available products from database  
- ➕ Add items to cart  
- 🔄 Update item quantity  
- ❌ Remove items from cart  
- 🧮 Calculate total bill with discount  
- 💾 Store orders in MySQL database  
- 🔄 Transaction management (commit/rollback)  
- 🛍️ Order history with item-level details  

---

## 🧰 Technologies Used

- **Language:** Java (JDK 8+)  
- **Database:** MySQL  
- **Connectivity:** JDBC  
- **IDE:** VS Code / IntelliJ  

---

## 📁 Project Structure
shopping_cart/
│── MainApp.java
│── Product.java
│── CartItem.java
│── ProductService.java
│── CartService.java
│── OrderService.java
│── DBConnection.java


---

## 🗄️ Database Design

### Tables Used:
- `products` → Stores product details  
- `orders` → Stores order summary  
- `order_items` → Stores items of each order  

---

## ⚙️ Setup Instructions

### 1️⃣ Clone Repository

git clone https://github.com/your-username/bytebazaar-shopping-cart-java.git
cd bytebazaar-shopping-cart-java
Add MySQL Connector

### 2️⃣ Add MySQL Connector
Download MySQL Connector JAR and place it in the project folder.

### 3️⃣ Compile & Run
javac -cp ".;mysql-connector-j-8.0.33.jar" shopping_cart/*.java
java -cp ".;mysql-connector-j-8.0.33.jar" shopping_cart.MainApp
---
## 🧠 System Flow
View Products
Add to Cart
View / Remove Cart Items
Checkout
Store Order in Database
Update Stock
---
## 📊 Sample Output
Welcome to ByteBazaar 🛒
1. View Products
2. Add to Cart
3. View Cart
4. Checkout
5. Exit
---
## 🔮 Future Enhancements
GUI (JavaFX / Swing)
Web version (Spring Boot)
User authentication system
Payment gateway integration
Recommendation system
---
## 👨‍💻 Authors
Krishnandu Das

