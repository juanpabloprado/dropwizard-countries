package com.juanpabloprado.countries.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Optional;
import com.hubspot.jackson.jaxrs.PropertyFiltering;
import com.juanpabloprado.countries.dao.CountryDAO;
import com.juanpabloprado.countries.representations.Country;
import com.juanpabloprado.countries.utilities.ErrorRepresentation;
import com.juanpabloprado.countries.utilities.LoggerJsonObject;
import com.juanpabloprado.countries.utilities.ValidatorConstraint;
import io.dropwizard.auth.Auth;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 12/18/2014.
 */
@Path("/v1/countries")
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryResource.class);

    private final CountryDAO countryDAO;

    public CountryResource(DBI jdbi) {
        countryDAO = jdbi.onDemand(CountryDAO.class);
    }

    @GET
    public Response getCountries(@Auth Boolean isAuthenticated){
        List<Country> countries = countryDAO.getCountries();
        return Response.ok(countries).build();
    }

    @GET
    @Path("/{code}")
    @PropertyFiltering
    public Response getCountry(@PathParam("code") String code, @Auth Boolean isAuthenticated) {
        Country country = countryDAO.getCountry(code);
        return Response.ok(country).build();
    }

    @POST
    public Response createCountry(@Valid Country country, @Auth Boolean isAuthenticated) throws JsonProcessingException, URISyntaxException {
        LoggerJsonObject.logObject(country, LOGGER);
        countryDAO.createCountry(country);
        return Response.created(new URI(country.getCode())).entity(country).build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteCountry(@PathParam("code") String code, @Auth Boolean isAuthenticated) {
        countryDAO.deleteCountry(code);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{code}")
    public Response updateContact(@PathParam("code") String code,@Valid Country country, @Auth Boolean isAuthenticated) throws JsonProcessingException {
        LoggerJsonObject.logObject(country, LOGGER);
        countryDAO.updateCountry(code, country);
        return Response.ok(country).build();
    }
}
