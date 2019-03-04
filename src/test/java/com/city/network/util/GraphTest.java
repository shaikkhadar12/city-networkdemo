package com.city.network.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.city.network.entity.Route;

@RunWith(SpringRunner.class)
public class GraphTest {
	@Before
	public void setUp() {

	}

	@Test
	public void test() {
		Graph graph = loadGraphWithData();

		assertTrue(graph.areNodesConnected("Boston", "Newark"));
		assertTrue(graph.areNodesConnected("Boston", "Dallas"));
		assertFalse(graph.areNodesConnected("Boston", "SanFrancisco"));

	}

	private Graph loadGraphWithData() {
		// Test Data
		Route<String> rt1 = new Route<String>("Boston", "Newark");
		Route<String> rt2 = new Route<String>("Newark", "Dallas");
		List<Route<String>> cityList = new ArrayList<Route<String>>();
		cityList.add(rt1);
		cityList.add(rt2);
		Graph graph = new Graph(cityList);
		return graph;
	}

}
