package it.univaq.swa.webmarket.rest.security;

import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
public class AuthenticationRes {

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password") String password, @Context UriInfo uriInfo){

        if(AuthHelpers.getInstance().authenticateUser(username, password)){
            String token = AuthHelpers.getInstance().issueToken(username, uriInfo);
            return Response.ok().header(AUTHORIZATION, "Bearer "+ token).build();
        }
        return Response.status(UNAUTHORIZED).build();
    }

    @DELETE
    @Path("logout")
    @Logged
    public Response logout(@Context ContainerRequestContext request)
    {
        AuthHelpers.getInstance().revokeToken((String)request.getProperty("token"));
        return Response.noContent().build();
    }

    @GET
    @Logged
    @Path("refresh")
    public Response refresh(@Context ContainerRequestContext request, @Context UriInfo uriInfo)
    {
        String username = (String) request.getProperty("username");
        String refreshedToken = AuthHelpers.getInstance().issueToken(username, uriInfo);

        return Response.ok().header(AUTHORIZATION, "Bearer "+refreshedToken).build();
    }
}
