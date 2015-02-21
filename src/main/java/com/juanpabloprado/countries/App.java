package com.juanpabloprado.countries;

import com.google.common.cache.CacheBuilderSpec;
import com.hubspot.jackson.jaxrs.PropertyFilteringMessageBodyWriter;
import com.juanpabloprado.countries.resources.ClientResource;
import com.juanpabloprado.countries.resources.CountryResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Hello world!
 *
 */
public class App extends Application<CountriesConfiguration>
{
    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<CountriesConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));

        bootstrap.addBundle(new MigrationsBundle<CountriesConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(CountriesConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(new ViewBundle());

    }

    @Override
    public void run(CountriesConfiguration configuration, Environment environment) throws Exception {
        configureCors(environment);
        environment.jersey().register(new PropertyFilteringMessageBodyWriter());

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        environment.jersey().register(new CountryResource(jdbi));

        // build the client and add the resource to the environment
        final Client client = new JerseyClientBuilder(environment).build("REST Client");
        client.addFilter(new HTTPBasicAuthFilter("wsuser", "wspassword"));
        environment.jersey().register(new ClientResource(client));

        // Authenticator, with caching support (CachingAuthenticator)
        CachingAuthenticator<BasicCredentials, Boolean> authenticator = new CachingAuthenticator<BasicCredentials, Boolean>(
                environment.metrics(),
                new CountriesAuthenticator(jdbi),
                CacheBuilderSpec.parse("maximumSize=10000, expireAfterAccess=10m"));

        // Register the authenticator with the environment
        environment.jersey().register(new BasicAuthProvider<Boolean>(
                authenticator, "Web Service Realm"));
    }

    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }
}
