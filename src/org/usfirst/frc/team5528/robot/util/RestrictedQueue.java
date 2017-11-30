package org.usfirst.frc.team5528.robot.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class RestrictedQueue<E> implements Queue<E> {

	private int maxSize;
	private Queue<E> queue;
	
	public RestrictedQueue(int maxSize) {
		this.maxSize = maxSize;
		queue = new ArrayDeque<>(maxSize);
	}
	
	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return queue.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}

	@Override
	public Object[] toArray() {
		return queue.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return queue.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		return queue.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return queue.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return queue.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return queue.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return queue.retainAll(c);
	}

	@Override
	public void clear() {
		queue.clear();
	}

	@Override
	public boolean add(E e) {
		if(queue.size() >= maxSize)
			throw new IllegalStateException("Maximum size of the queue.");
		
		queue.add(e);
		return true;
	}

	@Override
	public boolean offer(E e) {
		if(queue.size() >= maxSize)
			return false;
		
		queue.add(e);
		return true;
	}

	@Override
	public E remove() {
		return queue.remove();
	}

	@Override
	public E poll() {
		return queue.poll();
	}

	@Override
	public E element() {
		return queue.element();
	}

	@Override
	public E peek() {
		return queue.peek();
	}
	
	
}
