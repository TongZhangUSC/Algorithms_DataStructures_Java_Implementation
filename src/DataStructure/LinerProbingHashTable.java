package DataStructure;

public class LinerProbingHashTable<Key, Value> {
	private static final int INIT_CAPACITY=4;
	
	private int m;
	private int n;
	private Key[] keys;
	private Value[] values;
	
	public LinerProbingHashTable() {
		this(INIT_CAPACITY);
	}
	
	public LinerProbingHashTable(int capacity) {
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		values = (Value[]) new Object[m];
	}
	
	public int size() {
        return n;
    }
	
	public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
	public boolean isEmpty() {
        return size() == 0;
    }
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
		//http://stackoverflow.com/questions/9380670/why-does-java-use-hash-0x7fffffff-tab-length-to-decide-the-index-of-a-key
	}
	
	public void resize(int capacity) {
		LinerProbingHashTable<Key, Value> tmp = new LinerProbingHashTable<>(capacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				tmp.put(keys[i], values[i]);
			}
		}
		m = tmp.m;
		keys = tmp.keys;
		values = tmp.values;
	}
	
	public void put(Key key, Value value) {
		if (key == null) throw new IllegalArgumentException("the first argument to put() is null");
		
		if (value == null) {
			delete(key);
			return;
		}
		
		if (n >= m)	resize(2*m);//??m/2
		
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key)) {
				values[i] = value;
				return;
			}
		}
		keys[i] = key;
		values[i] = value;
		n++;
	}
	
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m){
			if(keys[i].equals(key))
				return values[i];
		}
		return null;
	}
		
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		
		int i = hash(key);
		while (!keys[i].equals(key)) 
			i = (i + 1) % m;
		keys[i] = null;
		values[i] = null;
		n--;
		
		for (i = (i + 1) % m; keys[i] != null; i = (i + 1) % m) {
			Key keyToRehash = keys[i];
			Value valueToRehash = values[i];
			keys[i] = null;
			values[i] = null;
			n--;
			put(keyToRehash, valueToRehash);
		}
		
		if (n > 0 && n <= m/4) resize(m/2);
		
		assert check();
	}
	
	/** 什么意思啊？？
	 *  integrity check  
	 *  don't check after each put() because integrity not maintained during a delete()
	 */
    private boolean check() {
        // check that hash table is at most full
        if (m < n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }
        // check that each key in table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != values[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + values[i]);
                return false;
            }
        }
        return true;
    }
    
//    /**
//     * Returns all keys in this symbol table as an {@code Iterable}.
//     * To iterate over all of the keys in the symbol table named {@code st},
//     * use the foreach notation: {@code for (Key key : st.keys())}.
//     *
//     * @return all keys in this symbol table
//     */
//    public Iterable<Key> keys() {
//        Queue<Key> queue = new Queue<Key>();
//        for (int i = 0; i < m; i++)
//            if (keys[i] != null) queue.enqueue(keys[i]);
//        return queue;
//    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinerProbingHashTable<String, Integer> st = new LinerProbingHashTable<String, Integer>();
//        for (int i = 0; !StdIn.isEmpty(); i++) {
//            String key = StdIn.readString();
//            st.put(key, i);
//        }
//        
//        Queue<String> q = (Queue<String>) st.keys();
//        // print keys
//        for (String s : q) 
//            StdOut.println(s + " " + st.get(s)); 
	}

}
