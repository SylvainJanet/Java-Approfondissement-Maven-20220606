package classes;

public class CartLine {

	private Product product;
	private int qty;
	
	public CartLine() {
		super();
	}

	public CartLine(Product product, int qty) {
		super();
		this.product = product;
		this.qty = qty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return this.qty * this.product.getPrice();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(product.getDescription()).append(" - x").append(qty).append(" : ").append(getTotal()).append("€");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartLine other = (CartLine) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
}
