package algo;


import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Queue<Item> implements Iterable<Item>{

	private Node<Item> first;
	private Node<Item> last;
	private int size;
	
	public Queue() {
		first = last= null;
		size = 0;		
	}
	
	private class Node<Item>{
		private Item item;
		private Node<Item> next;
		
		public Node(Item item) {
			this.item = item;
			this.next = null;	
		}
	}
	
	public int size() {return size; }
	public boolean isEmpty() {return first == null;}
	
	public void enqueue(Item item) {
		Node<Item> old = last;
		last = new Node<Item>(item);
		if(isEmpty()) first = last;
		else	      old.next = last;
		size++;
	}
	
	public Item dequeue() {
		if(isEmpty()) throw new NoSuchElementException(" Queue  is Under Flow ");
		Item item = first.item;
		first = first.next;
		size--;
		return item;
	}
	
	public Item peek() {
		if(isEmpty()) throw new NoSuchElementException(" Queue  is Under Flow ");
		return first.item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator(first) ;
	}
	
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current ;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		public boolean hasNext() { return current != null;}
		public void remove() { throw new UnsupportedOperationException();}
		public Item next() {
			if(isEmpty()) throw new NoSuchElementException(" Stack is Under Flow ");
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	public String toString() {
		StringBuilder string = new StringBuilder();
		for(Item item : this)
		{
			string.append(item);
			string.append(" ");
		}
		return string.toString();	
	}

public static void main(String []agrs) throws IOException {
		
//		BufferedReader br = new BufferedReader(new FileReader("src/files/input.txt"));
//		String str = br.readLine();
		Scanner sc = new Scanner(new FileReader("src/files/input.txt"));
		Queue<String> queue = new Queue<String>();

		while(sc.hasNext())
		{
			String item = sc.next();
			if(!item.equals("-"))
				queue.enqueue(item);
			else if(!queue.isEmpty())
				System.out.print(queue.dequeue() +" ");
		}
			
		
		System.out.println("( " + queue.size() +" left On Queue )" );
		Iterator<String> it = queue.iterator();
//		while(it.hasNext())
//		{
//			System.out.println(it.next());
//		}
//		for(String item :stack)
//			System.out.print(item + " ");
//
//		

	}
	
}
