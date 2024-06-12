package it.univaq.swa.webmarket.rest.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class CORSFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "*,Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Custom-header,X-Requested-With, content-type, access-control-allow-origin, access-control-allow-methods, access-control-allow-headers");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, OPTIONS, PUT, DELETE, HEAD");
        responseContext.getHeaders().add(
                "Access-Control-Expose-Headers",
                "Location,Authorization, *");
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        if (requestContext.getHeaderString("Origin") != null && requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            requestContext.abortWith(Response.ok().build());
        }
    }
}