package com.juanpabloprado.countries.dao;

import com.juanpabloprado.countries.dao.mappers.CountryMapper;
import com.juanpabloprado.countries.representations.Country;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by Juan on 12/18/2014.
 */
public interface CountryDAO {

    @SqlUpdate("insert into country (code, name) values ( :code, :name)")
    void createCountry(@BindBean Country country);

    @Mapper(CountryMapper.class)
    @SqlQuery("select code, name from country")
    List<Country> getCountries();

    @SqlQuery("select count(*) from country where code = :code")
    int getCountry(@Bind("code") String code);
}
