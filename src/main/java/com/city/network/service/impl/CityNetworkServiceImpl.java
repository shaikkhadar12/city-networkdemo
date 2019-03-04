package com.city.network.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.network.entity.Route;
import com.city.network.repository.CityNetworkRepository;
import com.city.network.service.CityNetworkService;
import com.city.network.util.Graph;

@Service
public class CityNetworkServiceImpl implements CityNetworkService {

	@Autowired
	CityNetworkRepository repo;

	Map<String, Graph> cache = new HashMap();

	/**
	 * This method check if Origin and Destination are connected
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isConnectivityExists(String origin, String destination) {
		Graph graph = loadGraph();
		return graph.areNodesConnected(origin, destination);
	}

	/**
	 * This method read resource and load Graph This method should be cached to
	 * avoid reading of resource and loading graph every time For demo
	 * implementation we're using HashMap as cache, in real time it should be
	 * EHCache/ Redis and should clear cache based on frequency of data or event
	 * 
	 * @return Graph
	 */
	private Graph loadGraph() {
		if (cache.get("graph") == null) {
			List<Route<String>> routeRepo = repo.fetchConnectedCities();
			Graph graph = new Graph(routeRepo);
			cache.put("graph", graph);
		}
		return cache.get("graph");
	}

}
