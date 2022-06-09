package o07.genericite;

import java.util.List;

/*
 * 
 * Pour indiquer qu'une classe ou une interface est générique, il faut ajouter la liste des
 * types génériques entre < >
 * 
 */
public class GenericClass<T> {

	private List<T> list;
	private T element;
	private int nbr;
	
	public GenericClass() {
		super();
	}
	public GenericClass(List<T> list, T element, int nbr) {
		super();
		this.list = list;
		this.element = element;
		this.nbr = nbr;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	
}
