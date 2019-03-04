package com.city.network.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "city.network.repo=cityNetwork.txt", })
public class CityNetworkRepositoryTest {

	@TestConfiguration
	static class CityNetworkServiceImplTestContextConfiguration {
		@Bean
		public CityNetworkRepository cityNetworkRepository() {
			return new CityNetworkRepository();
		}
	}

	@Autowired
	CityNetworkRepository testClass;

	@Test
	public void testFetchConnectedCities() {
		Assert.assertEquals(16, testClass.fetchConnectedCities().size());
	}
}
