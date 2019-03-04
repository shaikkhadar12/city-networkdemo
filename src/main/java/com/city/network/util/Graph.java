package com.city.network.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.city.network.entity.Route;

public class Graph<E> {

	Vertex<E> adjascencyList[];

	/**
	 * This creates the Graph of routes
	 * 
	 * @param networkList
	 */
	public Graph(List<Route<E>> networkList) {
		List<E> uniqueCityList = new ArrayList(getCityList(networkList));
		adjascencyList = new Vertex[uniqueCityList.size()];

		for (int i = 0; i < uniqueCityList.size(); i++) {
			adjascencyList[i] = new Vertex(uniqueCityList.get(i), i);
		}

		networkList.forEach(route -> {
			int index1 = findVertex(route.getOrigin()).getIndex();
			Vertex<E> destinationVertex = findVertex(route.getDestination());

			adjascencyList[index1].addEdge(destinationVertex);

		});
	}

	/**
	 * This method returns the unique set of cities
	 * 
	 * @param networkList
	 * @return
	 */

	private Set<E> getCityList(List<Route<E>> networkList) {
		Set<E> uniqueList = new HashSet<E>();
		networkList.forEach(route -> {
			uniqueList.add(route.getOrigin());
			uniqueList.add(route.getDestination());
		});

		return uniqueList;
	}

	/**
	 * This method is used to find the vertex of a given node
	 * 
	 * @param node
	 * @return
	 */

	private Vertex<E> findVertex(E node) {
		for (int vertexIndex = 0; vertexIndex < adjascencyList.length; vertexIndex++) {
			if (adjascencyList[vertexIndex].getElement().equals(node)) {
				return adjascencyList[vertexIndex];
			}
		}
		return null;
	}

	/**
	 * This method returns if 2 nodes are connected from Graph
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public boolean areNodesConnected(E origin, E destination) {
		Vertex<E> originVertex = findVertex(origin);
		Vertex<E> destinationVertex = findVertex(destination);

		if (originVertex == null || destinationVertex == null)
			return false;
		return performDFS(originVertex, destinationVertex, new HashSet());

	}

	/**
	 * To perform DFS of Graph
	 * 
	 * @param origin
	 * @param destination
	 * @param visitedNodes
	 * @return
	 */
	private boolean performDFS(Vertex<E> origin, Vertex<E> destination, HashSet visitedNodes) {
		visitedNodes.add(origin);

		if (origin.getIndex() == destination.getIndex())
			return true;

		List<Vertex<E>> edges = adjascencyList[origin.getIndex()].getAdjacencyList();

		for (int i = 0; i < edges.size(); i++) {
			Vertex<E> intermediate = edges.get(i);
			if (!visitedNodes.contains(intermediate)) {
				if (performDFS(intermediate, destination, visitedNodes))
					return true;
			}
		}

		return false;
	}

}
