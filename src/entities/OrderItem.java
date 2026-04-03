package entities;

public class OrderItem {

	private Product product;
	private Integer quantity;
	
	public OrderItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public OrderItem() {
		
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public double subTotal() {
		return product.getPrice() * quantity;
	}
	
	public String toString() {
		return product.getName()
				+ " - "
				+ "Quantidade: " 
				+ quantity
				+ " - Subtotal: "
				+ String.format("%.2f", subTotal());
	}
}
