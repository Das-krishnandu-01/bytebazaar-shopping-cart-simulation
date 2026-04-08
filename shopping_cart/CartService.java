package shopping_cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CartService {
    private List<CartItem> cart = new ArrayList<>();
    private ProductService productService = new ProductService();

    // 🔹 Add to Cart
    public void addToCart(int productId, int quantity) {
        try {
            if (quantity <= 0) {
                System.out.println("Invalid quantity!");
                return;
            }

            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("Product not found!");
                return;
            }

            if (quantity > product.getStock()) {
                System.out.println("Insufficient stock! Available: " + product.getStock());
                return;
            }

            // Check if already in cart
            for (CartItem item : cart) {
                if (item.getProduct().getId() == productId) {
                    int newQty = item.getQuantity() + quantity;

                    if (newQty > product.getStock()) {
                        System.out.println("Cannot add. Total quantity exceeds stock.");
                        return;
                    }

                    item.setQuantity(newQty);
                    System.out.println("Cart updated.");
                    return;
                }
            }

            cart.add(new CartItem(product, quantity));
            System.out.println("Added to cart.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 🔥 View Cart with Scanner (FIXED VERSION)
    public void viewCart(Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        while (true) {
            System.out.println("\n========= Your Cart =========");
            double total = 0;

            for (CartItem item : cart) {
                System.out.println(item);
                total += item.getTotalPrice();
            }

            System.out.printf("Total Estimate: %.2f\n", total);

            // Cart options
            System.out.println("\n1. Remove Item");
            System.out.println("2. Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID to remove: ");
                    int removeId = scanner.nextInt();
                    removeFromCart(removeId);
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // 🔹 Remove Item
    public void removeFromCart(int productId) {
        Iterator<CartItem> iterator = cart.iterator();

        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == productId) {
                iterator.remove();
                System.out.println("Item removed from cart.");
                return;
            }
        }

        System.out.println("Item not found in cart.");
    }

    // 🔹 Get Cart
    public List<CartItem> getCartItems() {
        return cart;
    }

    // 🔹 Clear Cart
    public void clearCart() {
        cart.clear();
    }
}