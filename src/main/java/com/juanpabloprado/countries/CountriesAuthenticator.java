package com.juanpabloprado.countries;

import com.google.common.base.Optional;
import com.juanpabloprado.countries.dao.UserDAO;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Juan on 12/24/2014.
 */
public class CountriesAuthenticator implements Authenticator<BasicCredentials, Boolean> {
    private final UserDAO userDao;

    public CountriesAuthenticator(DBI jdbi) {
        userDao = jdbi.onDemand(UserDAO.class);
    }

    @Override
    public Optional<Boolean> authenticate(BasicCredentials basicCredentials) {
        boolean validUser = (userDao.getUser(basicCredentials.getUsername(), basicCredentials.getPassword()) == 1);
        if (validUser) {
            return Optional.of(true);
        }
        return Optional.absent();
    }
}
