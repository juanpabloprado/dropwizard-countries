package com.juanpabloprado.countries.dao;

import com.google.common.base.Optional;
import com.hubspot.rosetta.jdbi.BindWithRosetta;
import com.juanpabloprado.countries.representations.Country;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import java.util.List;

/**
 * Created by Juan on 12/18/2014.
 */
public interface CountryDAO {

    @SqlUpdate("insert into country (code, name) values ( :code, :name)")
    void createCountry(@BindWithRosetta Country country);

    @SqlUpdate("update country set code = :code, name = :name where code = :id")
    void updateCountry(@Bind("id") String code, @BindWithRosetta Country country);

    @SqlUpdate("delete from country where code = :code")
    void deleteCountry(@Bind("code") String code);

    @SqlQuery("select code, name from country")
    List<Country> getCountries();

    @SingleValueResult
    @SqlQuery("select * from country where code = :code")
    Optional<Country> getCountry(@Bind("code") String code);
}
