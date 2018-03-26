package algo;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    private class  Node<Item>{
   	private Item item;
   	private Node<Item> next;
   	Node(Item item){
   	    this.item = item;
   	    next = null;
   	}
       }
       
       public Stack() {
	   first = null;
	   n=0;
       }
     public boolean isEmpty() {return first==null; }
     public int size() {return n; }
     public Iterator<Item> iterator() {
		return new StackIterator(first) ;
	}
	
	private class StackIterator<Item> implements Iterator<Item>{
		private Node<Item> current ;
		
		public StackIterator(Node<Item> first) {
			current = first;
		}
		public boolean hasNext() { return current != null;}
		public void remove() { throw new UnsupportedOperationException();}
		public Item next() {
			if (isEmpty()) {
			    throw new NoSuchElementException(" Stack is Under Flow ");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}

     public void push(Item item)
     {
	 Node<Item> old = first;
	 first = new Node(item);
	 first.next = old;
	 n++; 
     }
     public Item peek() {
	 if(isEmpty()) throw new NoSuchElementException();
	 return first.item;
     }

     public Item pop() {
	 if(isEmpty()) throw new NoSuchElementException();
	 Item item = first.item;
	 first = first.next;
	 --n;
	 return item;
     }
     
     public String toString() {
	 StringBuffer result = new StringBuffer();
	 for(Item item : this)
	 {
	     result.append("item");
	     result.append("");
	 }
	 return result.toString();
     }
}
