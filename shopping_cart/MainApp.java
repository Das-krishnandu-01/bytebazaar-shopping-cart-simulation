package shopping_cart;

import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductService productService = new ProductService();
    private static CartService cartService = new CartService();
    private static OrderService orderService = new OrderService();

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println(" Welcome to ByteBazaar ");
        System.out.println("Your Smart Shopping Destination");
        System.out.println("=========================================\n");

        while (true) {
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;

            // Input validation
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // clear invalid input
                continue;
            }

            switch (choice) {

                case 1:
                    productService.displayProducts();
                    break;

                case 2:
                    try {
                        System.out.print("Enter Product ID: ");
                        int pid = scanner.nextInt();

                        System.out.print("Enter Quantity: ");
                        int qty = scanner.nextInt();

                        cartService.addToCart(pid, qty);
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    cartService.viewCart(scanner); // 🔥 updated
                    break;

                case 4:
                    if (cartService.getCartItems().isEmpty()) {
                        System.out.println("Cart is empty. Cannot checkout.");
                    } else {
                        orderService.placeOrder(cartService.getCartItems());
                        cartService.clearCart();
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using ByteBazaar <3");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}