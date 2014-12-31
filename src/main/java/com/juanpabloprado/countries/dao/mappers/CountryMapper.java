package com.juanpabloprado.countries.dao.mappers;

import com.juanpabloprado.countries.representations.Country;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Juan on 12/18/2014.
 */
public class CountryMapper implements ResultSetMapper<Country> {
    @Override
    public Country map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Country(resultSet.getString("code"), resultSet.getString("name"));
    }
}
