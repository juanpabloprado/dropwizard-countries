package com.juanpabloprado.countries.views;

import com.juanpabloprado.countries.representations.Country;
import io.dropwizard.views.View;
/**
 * Created by Juan on 2/16/2015.
 */
public class CountryView extends View {

    private final Country country;

    public CountryView(Country country) {
        super("/views/country.mustache");
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}
