package com.city.network.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.city.network.entity.Route;

@Component
public class CityNetworkRepository {

	@Value("${city.network.repo}")
	private String file;

	Resource resource;

	private static final Logger LOGGER = LoggerFactory.getLogger(CityNetworkRepository.class);

	@PostConstruct
	public void init() throws IOException {

		resource = new ClassPathResource(file);

	}

	/**
	 * This method fetch and returns the Cities Route list
	 * 
	 * @return List
	 */
	public List<Route<String>> fetchConnectedCities() {
		List<Route<String>> connectedCities = new ArrayList<>();

		InputStream resourceAsStream;
		try {
			resourceAsStream = resource.getInputStream();

			Scanner scanner = new Scanner(resourceAsStream);

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] split = line.split(",");
				Route<String> connectedCity = new Route<>(split[0].trim(), split[1].trim());
				connectedCities.add(connectedCity);

			}
			scanner.close();
		} catch (IOException e) {
			LOGGER.info("Error while reading file");
		}
		return connectedCities;

	}
}
