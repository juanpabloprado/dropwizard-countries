package com.juanpabloprado.countries.representations;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Juan on 12/18/2014.
 */
public class Country {

    public static final String TAG = Country.class.getSimpleName();

    @NotBlank
    @Length(min = 1, max = 3)
    private final String code;

    @NotBlank
    @Length(min = 2, max = 50)
    private final String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Country() {
        code = null;
        name = null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
