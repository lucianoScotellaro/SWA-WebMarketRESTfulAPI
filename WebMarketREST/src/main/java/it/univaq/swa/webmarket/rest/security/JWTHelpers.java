package it.univaq.swa.webmarket.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.ws.rs.core.UriInfo;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Date;

public class JWTHelpers {

    private static JWTHelpers instance = null;
    private SecretKey key;

    private JWTHelpers()
    {
        KeyGenerator keyGenerator;
        try{
            keyGenerator = KeyGenerator.getInstance("HmacSha256");
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public SecretKey getKey()
    {
        return key;
    }

    public String issueToken(String username, UriInfo uriInfo)
    {
        Key key = getKey();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(15L).toInstant()))
                .signWith(key)
                .compact();
    }

    public String validateToken(String token)
    {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    public void revokeToken(String token)
    {

    }

    public static JWTHelpers getInstance()
    {
        if(instance == null){
            instance = new JWTHelpers();
        }
        return instance;
    }
}
