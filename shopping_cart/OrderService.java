package shopping_cart;

import java.sql.*;
import java.util.List;

public class OrderService {

    public void placeOrder(List<CartItem> cartItems) {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            double subtotal = 0;
            for (CartItem item : cartItems) {
                subtotal += item.getTotalPrice();
            }

            double discount = subtotal > 5000 ? subtotal * 0.1 : 0;
            double finalAmount = subtotal - discount;

            System.out.printf("Subtotal: %.2f\n", subtotal);
            System.out.printf("Discount: %.2f\n", discount);
            System.out.printf("Final Amount: %.2f\n", finalAmount);

            // Insert order
            String orderSql = "INSERT INTO orders (total_amount) VALUES (?)";
            PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setDouble(1, finalAmount);
            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) orderId = rs.getInt(1);

            // Insert order items 🔥
            String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement itemStmt = conn.prepareStatement(itemSql);

            for (CartItem item : cartItems) {
                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, item.getProduct().getId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setDouble(4, item.getProduct().getPrice());
                itemStmt.executeUpdate();
            }

            // Update stock
            String stockSql = "UPDATE products SET stock = stock - ? WHERE product_id = ?";
            PreparedStatement stockStmt = conn.prepareStatement(stockSql);

            for (CartItem item : cartItems) {
                stockStmt.setInt(1, item.getQuantity());
                stockStmt.setInt(2, item.getProduct().getId());
                stockStmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Order (ID: " + orderId + ") placed successfully!");

        } catch (Exception e) {
            System.out.println("Order failed. Rolling back...");
            try { if (conn != null) conn.rollback(); } catch (Exception ex) {}
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}