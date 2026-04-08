package shopping_cart;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s Price: %-8.2f Qty: %-3d Subtotal: %.2f", 
            product.getName(), product.getPrice(), quantity, getTotalPrice());
    }
}
