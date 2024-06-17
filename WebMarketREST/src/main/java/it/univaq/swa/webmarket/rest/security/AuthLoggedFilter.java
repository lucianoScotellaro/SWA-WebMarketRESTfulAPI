package it.univaq.swa.webmarket.rest.security;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

@Provider
@Logged
@Priority(Priorities.AUTHORIZATION)
public class AuthLoggedFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException
    {
        String authHeader = request.getHeaderString(AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith("Bearer "))
        {
            String token = authHeader.substring("Bearer".length()).trim();
            if (!token.isEmpty())
            {
                try
                {
                    String username = AuthHelpers.validateToken(token);
                    if (username != null)
                    {
                        request.setProperty("token", token);
                        request.setProperty("username", username);
                    }
                    else
                    {
                        request.abortWith(Response.status(UNAUTHORIZED).build());
                    }
                }
                catch (Exception e)
                {
                    request.abortWith(Response.status(UNAUTHORIZED).build());
                }
            }
            else
            {
                request.abortWith(Response.status(UNAUTHORIZED).build());
            }
        }
        else
        {
            request.abortWith(Response.status(UNAUTHORIZED).build());
        }
    }
}
