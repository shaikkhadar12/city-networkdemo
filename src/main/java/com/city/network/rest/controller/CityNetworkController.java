package com.city.network.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.city.network.model.ResponseMessage;
import com.city.network.service.CityNetworkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/connected")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "City Network API - tell if 2 cities are connected", produces = "application/json")
public class CityNetworkController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityNetworkController.class);

	@Autowired
	CityNetworkService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{origin}/{destination}")
	@ApiOperation(value = "Submit Origin/Destination and you'll get if they are connected", response = Response.class)
	@ApiResponses(value = { // Swagger Annotation
			@ApiResponse(code = 200, message = "Resource found"),
			@ApiResponse(code = 404, message = "Resource not found") })
	public Response areCitiesConnected(@ApiParam @PathParam("origin") String origin,
			@ApiParam @PathParam("destination") String destination) {
		LOGGER.info("Origin -{};Destination-{}", origin, destination);
		boolean areCitiesConnected = service.isConnectivityExists(origin, destination);
		LOGGER.info("Are Cities connected {}", areCitiesConnected);
		ResponseMessage response = new ResponseMessage();

		if (areCitiesConnected) {
			response.setMsg("yes");

			return Response.status(Response.Status.OK).entity(response).build();
		}
		response.setMsg("no");

		return Response.status(Response.Status.NOT_FOUND).entity(response).build();
	}

	@GET
	@Path("/ping")
	@ApiOperation( // Swagger Annotation
			value = "Get OK message if application is live", response = Response.class)
	@ApiResponses(value = { // Swagger Annotation
			@ApiResponse(code = 200, message = "Resource found"), })
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping() {
		ResponseMessage response = new ResponseMessage();
		response.setMsg("OK");
		return Response.status(Response.Status.OK).entity(response).build();
	}
}
