package algo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int size;

    Bag() {
	this.first = null;
	this.size = 0;
    }

    private class Node<Item> {
	private Item item;
	private Node<Item> next;

	public Node(Item item) {
	    this.item = item;
	    this.next = null;
	}
    }

    public void add(Item item) {

	Node<Item> old = first;
	first = new Node<Item>(item);
	first.next = old;
	size++;
    }

    public void removeItem() {
	Node<Item> current = first;
	while (current.next != null) {
	    System.out.println(current.item + "  " + current.next);
	    current = current.next;
	}
    }

    public int size() {
	return size;
    }

    public boolean isEmpty() {
	return first != null;
    }

    public Item remove() {
	Item item = first.item;
	first = first.next;
	size--;
	return item;
    }

    public Iterator<Item> iterator() {
	return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator {
	private Node<Item> current;

	public ListIterator(Node<Item> first) {
	    current = first;
	}

	@Override
	public boolean hasNext() {
	    return current != null;
	}

	public void remove() {
	    throw new UnsupportedOperationException();
	}

	public Item next() {

	    if (!hasNext())
		throw new NoSuchElementException();
	    Item item = current.item;
	    current = current.next;
	    return item;
	}

    }

    public static void main(String[] agrs) throws IOException {

	// BufferedReader br = new BufferedReader(new
	// FileReader("src/files/input.txt"));
	// String str = br.readLine();
	Bag<String> bag = new Bag<String>();
	try {
	    Scanner sc = new Scanner(new FileReader("src/files/input.txt"));
	    while (sc.hasNext()) {
		String item = sc.next();
		bag.add(item);
	    }

	} catch (Exception e) {
	    // TODO: handle exception
	}

	System.out.println("The Bag Size :" + bag.size());
	Iterator<String> it = bag.iterator();
	// while(it.hasNext())
	// {
	// System.out.println(it.next());
	// }
	for (String item : bag)
	    System.out.println(item);

    }

}
