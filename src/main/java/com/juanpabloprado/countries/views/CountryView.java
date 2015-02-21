package com.juanpabloprado.countries.views;

import com.juanpabloprado.countries.representations.Country;
import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/16/2015.
 */
public class CountryView extends View {

    private final List<Country> countries;

    public CountryView(List<Country> countries) {
        super("/views/country.mustache");
        this.countries = countries;
    }

    public CountryView(Country c) {
        super("/views/country.mustache");
        countries = new ArrayList<Country>();
        countries.add(c);
    }

    public List<Country> getCountries() {
        return countries;
    }
}
