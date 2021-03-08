package prog10;

import java.util.*;

public class ChainedHashTable<K, V> extends AbstractMap<K, V> {
	public static class Entry<K, V> implements Map.Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			return this.value = value;
		}

		Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	private final static int DEFAULT_CAPACITY = 5;
	private Entry<K, V>[] table = new Entry[DEFAULT_CAPACITY];
	private int size;

	private int hashIndex(Object key) {
		int index = key.hashCode() % table.length;
		if (index < 0)
			index += table.length;
		return index;
	}

	private Entry<K, V> find(Object key) {
		int index = hashIndex(key);
		for (Entry<K, V> node = table[index]; node != null; node = node.next)
			if (key.equals(node.key))
				return node;
		return null;
	}

	public boolean containsKey(Object key) {
		return find(key) != null;
	}

	public V get(Object key) {
		Entry<K, V> node = find(key);
		if (node == null)
			return null;
		return node.value;
	}

	public V put(K key, V value) {
		Entry<K, V> node = find(key);
		if (node != null) {
			V old = node.value;
			node.value = value;
			return old;
		}
		if (size == table.length)
			rehash(2 * table.length);
		int index = hashIndex(key);
		table[index] = new Entry<K, V>(key, value, table[index]);
		size++;
		return null;
	}

	public V remove(Object key) {
		// IMPLEMENT
		// Get the index for the key.
		int index = hashIndex(key);

		// What do you do if the linked list at that index is empty?
		if (table[index] == null)
			return null;

		// What do you do if the first element of the list has this key?
		if (key.equals(table[index].key)) {
			V value = table[index].value;
			table[index] = table[index].next;
			size--;
			return value;
		}

		// If the key is further down the list, make sure you keep track
		// of the pointer to the previous entry, because you will need to
		// change its next variable.
		for (int i = 0; i < table.length; i++) {
			for (Entry<K, V> node = table[index]; node != null; node = node.next) {
				if (key.equals(node.next.key)) {
					V value = node.next.value;
					node = node.next.next;
					size--;
					return value;
				}
			}

		}
		// Return null otherwise.
		return null;
	}

	private void rehash(int newCapacity) {
		// IMPLEMENT
		Entry<K, V>[] oldTable = table;
		table = new Entry[newCapacity];
		size = 0;
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null) {
				for (Entry<K, V> node = oldTable[i]; node != null; node = node.next) {
					put(node.key, node.value);
				}
			}
		}
	}

	private Iterator<Map.Entry<K, V>> entryIterator() {
		return new EntryIterator();
	}

	private class EntryIterator implements Iterator<Map.Entry<K, V>> {
		// EXERCISE
		int index = 0;
		Entry<K, V> entry = table[index];

		public boolean hasNext() {
			// EXERCISE
			for (int i = index; i < size; i++) {
				if (table[i] != null)
					return true;
			}
			return false;
		}

		public Map.Entry<K, V> next() {
			// EXERCISE
			while (entry == null) {
				index++;
				entry = table[index];
			}
			if (entry.next == null)
				return entry;
			return null;
		}

		public void remove() {
			for (int i = index; i < size; i++) {
				table[i] = table[i + 1];
			}
			size--;
		}
	}

	public Set<Map.Entry<K, V>> entrySet() {
		return new EntrySet();
	}

	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
		public int size() {
			return size;
		}

		public Iterator<Map.Entry<K, V>> iterator() {
			return entryIterator();
		}

		public void remove() {
		}
	}

	public String toString() {
		String ret = "------------------------------\n";
		for (int i = 0; i < table.length; i++) {
			ret = ret + i + ":";
			for (Entry<K, V> node = table[i]; node != null; node = node.next)
				ret = ret + " " + node.key + " " + node.value;
			ret = ret + "\n";
		}
		return ret;
	}

	public static void main(String[] args) {
		ChainedHashTable<String, Integer> table = new ChainedHashTable<String, Integer>();

		table.put("Brad", 46);
		System.out.println(table);
		table.put("Hal", 10);
		System.out.println(table);
		table.put("Kyle", 6);
		System.out.println(table);
		table.put("Lisa", 43);
		System.out.println(table);
		table.put("Lynne", 43);
		System.out.println(table);
		table.put("Victor", 46);
		System.out.println(table);
		table.put("Zoe", 6);
		System.out.println(table);
		table.put("Zoran", 76);
		System.out.println(table);
		
		for (String key : table.keySet())
			System.out.print(key + " ");
		System.out.println();

		table.remove("Zoe");
		System.out.println(table);
		table.remove("Kyle");
		System.out.println(table);
		table.remove("Brad");
		System.out.println(table);
		table.remove("Zoran");
		System.out.println(table);
		table.remove("Lisa");
		System.out.println(table);
	}
}
