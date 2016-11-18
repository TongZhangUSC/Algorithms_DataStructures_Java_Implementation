package util;

public class Node<T> {
	public Node<T> next;
	public T item;
	
	public Node() {
		this(null);
	}
	
	public Node(T item) {
		this.item = item;
	}
	
	public String toString() {
        return "value=" + item + " next=" + ((next != null) ? next.item : "NULL");
    }
}
