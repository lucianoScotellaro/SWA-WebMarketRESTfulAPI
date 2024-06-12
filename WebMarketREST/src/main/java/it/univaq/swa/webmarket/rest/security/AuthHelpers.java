package it.univaq.swa.webmarket.rest.security;

import jakarta.ws.rs.core.UriInfo;

public class AuthHelpers {

    private static AuthHelpers instance = null;
    private static JWTHelpers jwtHelpers;

    private AuthHelpers(){
        jwtHelpers = JWTHelpers.getInstance();
    }

    public static String validateToken(String token)
    {
        return jwtHelpers.validateToken(token);
    }

    public boolean authenticateUser(String username, String password)
    {
        return true;
    }

    public String issueToken(String username, UriInfo uriInfo){
        return jwtHelpers.issueToken(username, uriInfo);
    }

    public void revokeToken(String token){
        jwtHelpers.revokeToken(token);
    }

    public static AuthHelpers getInstance()
    {
        if(instance == null){
            instance = new AuthHelpers();
        }
        return instance;
    }

}
