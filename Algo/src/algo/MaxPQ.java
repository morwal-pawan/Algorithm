package algo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

//public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key>
public class MaxPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MaxPQ(int initCapacity) {
	// new Key[initCapacity]; as key may not be comparable
	pq = (Key[]) new Object[initCapacity + 1];
	n = 0;
    }

    public MaxPQ() {
	this(1);
    }

    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
	this(initCapacity);
	this.comparator = comparator;
    }

    public MaxPQ(Comparator<Key> comparator) {
	this(1);
	this.comparator = comparator;
    }

    public MaxPQ(Key[] keys) {
	n = keys.length;
	pq = (Key[]) new Object[n + 1];
	for (int i = 0; i < keys.length; i++)
	    pq[i + 1] = keys[i];
	for (int k = n / 2; k >= 1; k--)
	    sink(k);
    }

    public boolean isEmpty() {
	return n == 0;
    }

    public int size() {
	return n;
    }

    public Key max() {
	if (isEmpty())
	    throw new NoSuchElementException();
	return pq[1];
    }

    public void resize(int capacity) {
	if (capacity < this.size())
	    return;
	Key[] temp = (Key[]) new Object[capacity + 1];
	for (int k = 1; k < size(); k++)
	    temp[k] = pq[k];
	pq = temp;
    }

    public void insert(Key key) {
	if (size() == pq.length - 1)
	    resize(2 * pq.length);
	pq[++n] = key;
	swim(n);
    }

    public Key delMax() {
	if (isEmpty())
	    throw new NoSuchElementException("Priority queue underflow");
	Key max = pq[1];
	exchange(1, n);
	n--;
	sink(1);
	pq[n + 1] = null;
	if ((n > 0) && (n == (pq.length - 1) / 4))
	    resize(pq.length / 2);
	return max;
    }

    private void exchange(int k1, int k2) {
	Key swap = pq[k1]; pq[k1] = pq[k2]; pq[k2] = swap;
    }

    private boolean less(int k1, int k2) {
	if (comparator == null)
	    return ((Comparable<Key>) pq[k1]).compareTo(pq[k2]) < 0;
	else
	    return comparator.compare(pq[k1], pq[k2]) < 0;
    }

    private void swim(int k) {
	while (k > 1 && less(k / 2, k)) {
	    exchange(k / 2, k);
	    k = k / 2;
	}
    }

    private void sink(int k) {
	while (2 * k <= size()) {
	    int j = 2 * k;
	    if (less(j, j + 1))
		j = j + 1;
	    if (!less(k, j))
		break;
	    exchange(j, k);
	    k = j;
	}
    }

    @Override
    public Iterator<Key> iterator() {
	return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

	private MaxPQ<Key> copy;

	// add all items to copy of heap
	// takes linear time since already in heap order so no keys move
	public HeapIterator() {
	    if (comparator == null)
		copy = new MaxPQ<Key>(size());
	    else
		copy = new MaxPQ<Key>(size(), comparator);
	    for (int i = 1; i <= n; i++)
		copy.insert(pq[i]);
	}

	public boolean hasNext() {
	    return !copy.isEmpty();
	}

	public void remove() {
	    throw new UnsupportedOperationException();
	}

	public Key next() {
	    if (!hasNext())
		throw new NoSuchElementException();
	    return copy.delMax();
	}
    }

}
