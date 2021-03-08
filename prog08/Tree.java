package prog08;

import java.util.*;

public class Tree<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	private class Node<K extends Comparable<K>, V> implements Map.Entry<K, V> {
		K key;
		V value;
		Node left, right;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
	}

	private Node root;
	private int size;

	public int size() {
		return size;
	}

	/**
	 * Find the node with the given key.
	 * 
	 * @param key
	 *            The key to be found.
	 * @return The node with that key.
	 */
	private Node<K, V> find(K key, Node<K, V> root) {
		// EXERCISE:
		if (root == null)
			return null;
		int cmp = key.compareTo(root.key);
		if (cmp == 0)
			return root;
		if (cmp < 0)
			return find(key, root.left);
		else
			return find(key, root.right);
	}

	public boolean containsKey(Object key) {
		return find((K) key, root) != null;
	}

	public V get(Object key) {
		Node<K, V> node = find((K) key, root);
		if (node != null)
			return node.value;
		return null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Add key,value pair to tree rooted at root. Return root of modified tree.
	 */
	private Node<K, V> add(K key, V value, Node<K, V> root) {
		// EXERCISE:
		if (root == null) {
			return new Node<K, V>(key, value);
		}
		int cmp = key.compareTo(root.key);
		if (cmp < 0)
			root.left = add(key, value, root.left);
		else
			root.right = add(key, value, root.right);
		return root;
	}

	int depth(Node root) {
		if (root == null)
			return -1;
		return 1 + Math.max(depth(root.left), depth(root.right));
	}

	public V put(K key, V value) {
		// EXERCISE:
		Node<K, V> newNode = find(key, root);
		if (newNode != null)
			newNode.setValue(value);
		else {
			size++;
			root = add(key, value, root);
			return null;
		}
		return null;
	}

	public V remove(Object keyAsObject) {
		// EXERCISE: Delete these lines and implement remove.
		K key = (K) keyAsObject;

		Node<K, V> node = find(key, root);
		if (node == null)
			return null;
		root = remove(key, root);
		size--;
		return node.value;
	}

	private Node<K, V> remove(K key, Node<K, V> root) {
		// EXERCISE:
		if (key.compareTo(root.key) == 0)
			return removeRoot(root);
		if (key.compareTo(root.key) < 0)
			root.left = remove(key, root.left); 
			//needs to be set equal to root.left so that
			//it can recursively continue down the left branch
		else
			root.right = remove(key, root.right);
			//needs to be set equal to root.right so that
			//it can recursively continue down the right branch

		return root;
		//dont forget to return root when you're done
	}

	/**
	 * Remove root of tree rooted at root. Return root of BST of remaining nodes.
	 */
	private Node removeRoot(Node root) {
		// IMPLEMENT
		if (root.left == null)
			return root.right;
		if (root.right == null)
			return root.left;
		
		root.right = moveMinToRoot(root.right);

		// Put the left subtree (of the root) to the left of the right subtree.
		root.right.left = root.left;
		// Return the right subtree.
		return root.right;
	}

	/**
	 * Move the minimum key (leftmost) node to the root. Return the new root.
	 */
	private Node moveMinToRoot(Node root) {
		// IMPLEMENT
		
		Node min = null;
		if (root.left == null)
			return root;
		
		min = moveMinToRoot(root.left);
		root.left = min.right; //just take the right tree of the min 
		min.right = root; //correct
		
		return min;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		return null;
	}

	public String toString() {
		return toString(root, 0);
	}

	private String toString(Node root, int indent) {
		if (root == null)
			return "";
		String ret = toString(root.right, indent + 2);
		for (int i = 0; i < indent; i++)
			ret = ret + "  ";
		ret = ret + root.key + " " + root.value + "\n";
		ret = ret + toString(root.left, indent + 2);
		return ret;
	}

	public static void main(String[] args) {
		Tree<Character, Integer> tree = new Tree<Character, Integer>();
		String s = "balanced";

		for (int i = 0; i < s.length(); i++) {
			tree.put(s.charAt(i), i);
			System.out.print(tree);
			System.out.println("-------------");
		}

		for (int i = 0; i < s.length(); i++) {
			tree.remove(s.charAt(i));
			System.out.print(tree);
			System.out.println("-------------");
		}
	}
}