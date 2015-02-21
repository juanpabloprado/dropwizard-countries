package com.juanpabloprado.countries.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.juanpabloprado.countries.representations.Country;
import com.juanpabloprado.countries.views.CountryView;
import com.sun.jersey.api.client.*;
import java.util.List;

/**
 * Created by Juan on 2/16/2015.
 */
@Produces(MediaType.TEXT_HTML)
@Path("/v1/client/")
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private Client client;

    public ClientResource(Client client) {
        this.client = client;
    }

    @GET
    @Path("showCountry")
    public CountryView showCountry(@DefaultValue("MEX") @QueryParam("code") String code) throws JsonProcessingException {
        WebResource contactResource = client.resource("http://localhost:8080/v1/countries/" + code);
        Country c = contactResource.get(Country.class);
        return new CountryView(c);
    }

    @GET
    @Path("showCountries")
    public CountryView showCountries() throws JsonProcessingException {
        WebResource contactResource = client.resource("http://localhost:8080/v1/countries/");
        List<Country> c = contactResource.get(new GenericType<List<Country>>() {});
        return new CountryView(c);
    }

}
