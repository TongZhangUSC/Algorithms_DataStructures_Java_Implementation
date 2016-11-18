package DataStructure;

public class ResizableArray<T> {
	public static final int INIT_CAPACITY=16;
	
	private int size;
	private T[] array;
	
	public ResizableArray() {
		this(INIT_CAPACITY);
	}
	
	public ResizableArray(int capacity) {
		size = 0;
		array = (T[]) new Object[capacity];
	}
	
	public int size() {
		return size;
	}
	
	public int capacity() {
		return array.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T at(int index) {
		return array[index];
	}
	
	/**
	 * when you reach capacity, resize to double the size
	 * when popping an item, if size is 1/4 of capacity, resize to half
	 * */
	private void resize(int capacity) {
		T[] newArray = (T[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			newArray[i] = array[i];
		}
		size = capacity;
		array = newArray;
	}
	
	public void add(T value) {
		add(size, value);
	}
	
	public void add(int index, T value) {
		if(size >= array.length) resize(size * 2);//grow by twice
		if(index == size)	array[index++] = value;
		else {
			System.arraycopy(array, index, array, index + 1, array.length - index);
			array[index] = value;
		}
	}
	
	/**
	 * remove by index
	 * */
	public T remove(int index) {
		if (index < 0 || index >= size)	return null;
		T t = array[index];
		if (index != --size) 
			System.arraycopy(array, index + 1, array, index, array.length - index);
		array[size] = null;
		if (size >= INIT_CAPACITY && size <= array.length/4)//shrink to 50%
			resize(array.length/2);
		return t;
	}
	
	/**
	 * remove all items of given value
	 * */
	public void remove(T value) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(value))	remove(i);
		}
	}
	
	/**
	 * looks for value and returns first index with that value, -1 if not found
	 * */
	public int find(T value) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(value))	return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
