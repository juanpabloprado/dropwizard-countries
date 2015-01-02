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

    @SqlUpdate("update country set code = :code, name = :name where code = :id")
    void updateCountry(@Bind("id") String code, @BindBean Country country);

    @SqlUpdate("delete from country where code = :code")
    void deleteCountry(@Bind("code") String code);

    @Mapper(CountryMapper.class)
    @SqlQuery("select code, name from country")
    List<Country> getCountries();

    @Mapper(CountryMapper.class)
    @SqlQuery("select * from country where code = :code")
    Country getCountry(@Bind("code") String code);
}
