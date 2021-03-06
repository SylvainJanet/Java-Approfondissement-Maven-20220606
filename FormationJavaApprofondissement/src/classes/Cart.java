package classes;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartLine> lines;
	
	public Cart() {
		super();
		this.lines = new ArrayList<>();
	}

	public Cart(List<CartLine> lines) {
		super();
		this.lines = lines;
	}

	public List<CartLine> getLines() {
		return lines;
	}

	public void setLines(List<CartLine> lines) {
		this.lines = lines;
	}

	public double getTotal() {
		double res = 0;
		for (CartLine cl: lines) {
			res += cl.getTotal();
		}
		return res;
	}
	
	public void addLine(CartLine line) {
		// est ce que line.getProduct() est déjà
		// dans la liste ?
		int index = lines.indexOf(line);
		// renvoie le premier indice d'une ligne l
		// telle que l.equals(line)
		if (index == -1) {
			// le produit n'est pas dans la liste
			lines.add(line);
		} else {
			int newQty = lines.get(index).getQty() + line.getQty();
			lines.get(index).setQty(newQty);
		}
		
	}
	
	public void removeLine(CartLine line) {
		int index = lines.indexOf(line);
		if (index != -1) {
			int previousQty = lines.get(index).getQty();
			if (previousQty - line.getQty() > 0) {
				int newQty = previousQty - line.getQty();
				lines.get(index).setQty(newQty);
			} else {
				lines.remove(line);
			}
		}
	}
	
	public int count() {
		int res = 0;
		for (CartLine cl: lines) {
			res += cl.getQty();
		}
		return res;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Panier :").append("\n");
		for (CartLine cl : lines) {
			builder.append(cl.toString()).append("\n");
		}
		builder.append("TOTAL : ").append(getTotal()).append("€");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lines == null) ? 0 : lines.hashCode());
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
		Cart other = (Cart) obj;
		if (lines == null) {
			if (other.lines != null)
				return false;
		} else if (!lines.equals(other.lines))
			return false;
		return true;
	}
	
	
}
