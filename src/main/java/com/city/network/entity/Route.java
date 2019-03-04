package com.city.network.entity;

import java.util.Objects;

public class Route<E> {

	private E origin;
	private E destination;

	public Route(E origin, E destination) {
		super();
		this.origin = origin;
		this.destination = destination;
	}

	public E getOrigin() {
		return origin;
	}

	public void setOrigin(E origin) {
		this.origin = origin;
	}

	public E getDestination() {
		return destination;
	}

	public void setDestination(E destination) {
		this.destination = destination;
	}

	@Override
	public int hashCode() {
		return Objects.hash(origin, destination);
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}

}
