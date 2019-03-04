package com.city.network.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.city.network.entity.Route;
import com.city.network.repository.CityNetworkRepository;
import com.city.network.service.impl.CityNetworkServiceImpl;

@RunWith(SpringRunner.class)
public class CityNetworkServiceTest {
	@Before
	public void setUp() {
		// Test Data
		Route<String> rt1 = new Route<String>("Boston", "Newark");
		Route<String> rt2 = new Route<String>("Newark", "Dallas");
		List<Route<String>> cityList = new ArrayList<Route<String>>();
		cityList.add(rt1);
		cityList.add(rt2);

		Mockito.when(repo.fetchConnectedCities()).thenReturn(cityList);
	}

	@TestConfiguration
	static class CityNetworkServiceImplTestContextConfiguration {
		@Bean
		public CityNetworkService cityNetworkService() {
			return new CityNetworkServiceImpl();
		}
	}

	@MockBean
	CityNetworkRepository repo;

	@Autowired
	CityNetworkService service;

	@Test
	public void testIsConnectivityExists() {
		assertTrue(service.isConnectivityExists("Boston", "Newark"));
		assertTrue(service.isConnectivityExists("Boston", "Dallas"));
		assertFalse(service.isConnectivityExists("Boston", "SanFrancisco"));

	}

}
