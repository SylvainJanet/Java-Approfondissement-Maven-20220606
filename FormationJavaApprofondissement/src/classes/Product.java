package classes;

public class Product {

	private long id;
	private String description;
	private double price;

	public Product() {
		super();
	}

	public Product(long id, String description, double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
//		on devrait vérifier que le prix n'est pas négatif
		// vérifier valeur -> lancer une exception personnalisée dans le cas
		// problématique
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(description).append(" - ").append(price).append(" €");
		return builder.toString();
	}

}
