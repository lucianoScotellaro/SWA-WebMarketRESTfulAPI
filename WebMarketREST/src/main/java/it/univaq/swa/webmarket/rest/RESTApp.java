package it.univaq.swa.webmarket.rest;


import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;
import it.univaq.swa.webmarket.rest.jackson.ObjectMapperContextResolver;
import it.univaq.swa.webmarket.rest.resources.PurchaseRequestsResource;
import it.univaq.swa.webmarket.rest.security.AuthLoggedFilter;
import it.univaq.swa.webmarket.rest.security.AuthenticationRes;
import it.univaq.swa.webmarket.rest.security.CORSFilter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class RESTApp extends Application
{
    private final Set<Class<?>> classes;

    public RESTApp()
    {
        HashSet<Class<?>> c = new HashSet<>();

        //Resources
        c.add(PurchaseRequestsResource.class);
        c.add(AuthenticationRes.class);

        //Utils
        c.add(JacksonJsonProvider.class);
        c.add(ObjectMapperContextResolver.class);

        //Filters
        c.add(CORSFilter.class);
        c.add(AuthLoggedFilter.class);

        classes = Collections.unmodifiableSet(c);
    }


    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
