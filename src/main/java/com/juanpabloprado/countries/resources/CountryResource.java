package com.juanpabloprado.countries.resources;

import com.juanpabloprado.countries.dao.CountryDAO;
import com.juanpabloprado.countries.representations.Country;
import io.dropwizard.auth.Auth;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response getCountries( @Auth Boolean isAuthenticated){
        List<Country> countries = countryDAO.getCountries();
        return Response.ok(countries).build();
    }
}
