package com.city.network.util;

import java.util.LinkedList;

public class Vertex<E> {
	private final E element;
	private volatile LinkedList<Vertex<E>> adjacencyList;
	private int index;

	public Vertex(E element, int index) {
		super();
		this.element = element;
		this.index = index;
		this.adjacencyList = new LinkedList();
	}

	public void addEdge(Vertex<E> destination) {
		adjacencyList.add(destination);

	}

	public LinkedList<Vertex<E>> getAdjacencyList() {
		return adjacencyList;
	}

	public E getElement() {
		return element;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
