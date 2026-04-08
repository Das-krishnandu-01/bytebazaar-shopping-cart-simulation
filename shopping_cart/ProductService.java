package shopping_cart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return products;
    }

    public void displayProducts() {
        try {
            List<Product> products = getAllProducts();
            System.out.println("\n--- Available Products ---");
            System.out.println("ID    Name                 Price      Stock");
            System.out.println("----------------------------------------------");
            for (Product p : products) {
                System.out.println(p);
            }
            System.out.println("----------------------------------------------");
        } catch (Exception e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }
    }
    
    public Product getProductById(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE product_id=" + id)) {
            if (rs.next()) {
                 return new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }
        }
        return null;
    }
}
