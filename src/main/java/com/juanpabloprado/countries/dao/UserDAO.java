package com.juanpabloprado.countries.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * Created by Juan on 12/24/2014.
 */
public interface UserDAO {
    @SqlQuery("select count(*) from users where username = :username and password = :password")
    int getUser(@Bind("username") String username, @Bind("password") String password);
}
