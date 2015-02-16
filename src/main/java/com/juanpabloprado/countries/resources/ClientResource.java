package com.juanpabloprado.countries.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.juanpabloprado.countries.representations.Country;
import com.juanpabloprado.countries.views.CountryView;
import com.sun.jersey.api.client.*;
/**
 * Created by Juan on 2/16/2015.
 */
@Produces(MediaType.TEXT_HTML)
@Path("/v1/client/")
public class ClientResource {

    private Client client;

    public ClientResource(Client client) {
        this.client = client;
    }

    @GET
    @Path("showCountry")
    public CountryView showCountry(@QueryParam("code") String code) {
        WebResource contactResource = client.resource("http://localhost:8080/v1/countries/" + code);
        Country c = contactResource.get(Country.class);
        return new CountryView(c);
    }

}
