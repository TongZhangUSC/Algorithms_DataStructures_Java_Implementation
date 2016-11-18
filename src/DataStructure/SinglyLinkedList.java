package DataStructure;

import util.Node;

public class SinglyLinkedList<T> {
	private int size;
	private Node<T> head;
	
	public SinglyLinkedList() {
		size = 0;
		head = null;
	}

	public int size() {
		return size;
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	public void pushFront(T value) {
		this.add(0, value);
	}
	
	public void pushBack(T value) {
		this.add(size(), value);
	}
	
	public void add(int index, T value) {
		Node<T> tmp = new Node(value);
		if (head == null) {
			head = tmp;
		} else if (index == 0) {
			tmp.next = head;
			head = tmp;
		} else {
			Node<T> p = head;
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			tmp.next = p.next;
			p.next = tmp;
		}
		size++;
	}
	
	public T remove(int index) {
		if (head == null)	return null;
		
		Node<T> p = head;
		if (index == 0) {
			head = head.next;
			size--;
			return p.item;
		} 
		
		for (int i = 0; i < index - 1; i++)
			p = p.next;
		T t = p.next.item;
		p.next = p.next.next;
		size--;
		return t;
	}
	
	public boolean remove(T value) {
		if(head.item.equals(value)) {
			head = head.next;
			return true;
		}
		Node<T> p = head, q=head.next;
		while (q != null) {
			if(q.item.equals(value)) {
				p.next = q.next;
				return true;
			}
			p = p.next;
			q = q.next;
		}
		return false;
	}
	
	public T get(int index) {
		Node<T> p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.item;
	}
	
	public T value_n_from_end(int n) {
		Node<T> p = head,q = head;
		while (n-- > 0) {
			q = q.next;
		}
		while(q.next != null) {
			p = p.next;
			q = q.next;
		}
		return p.next.item;
	}
	
	public void reverse() {
		Node<T> first = head, cur = head.next;
		first.next = null;
		while (cur != null) {
			Node<T> next = cur.next;
			cur.next = first;
			first = cur;
			cur = next;
		}
		head = first;
	}
	
	public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<T> node = head;
        while (node != null) {
            builder.append(node.item).append(", ");
            node = node.next;
        }
        return builder.toString();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SinglyLinkedList<String> sl = new SinglyLinkedList<>();
		sl.pushBack("H");
		sl.pushBack("e");
		sl.pushBack("l");
		sl.pushBack("l");
		sl.pushBack("o");
		System.out.println(sl.toString());
		System.out.println("remove index 1 : " + sl.remove(1));
		System.out.println("remove last index : " + sl.remove(sl.size()-1));
		System.out.println(sl.toString());
		System.out.println("list size = " + sl.size());
		sl.pushFront(" ");
		sl.pushFront("Z");
		sl.pushFront("T");
		System.out.println(sl.toString());
		System.out.println("value_n_from_end(2) = " + sl.value_n_from_end(2));
		sl.reverse();
		System.out.println(sl.toString());
		System.out.println("remove ' ' : " + sl.remove(" "));
		System.out.println(sl.toString());
		System.out.println("remove index 0 : " + sl.remove(0));
		System.out.println("remove index 0 : " + sl.remove(0));
		System.out.println("remove index 0 : " + sl.remove(0));
		System.out.println(sl.toString());
		sl.reverse();
		System.out.println(sl.toString());
	}

}
