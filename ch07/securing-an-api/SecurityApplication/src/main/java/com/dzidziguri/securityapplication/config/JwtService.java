package com.dzidziguri.securityapplication.config;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //this is a secret key, using this we create signing tool
    //as we know digital signature is a combination of secret key(SHA256, SHA512) + encoding algorithm(RSA, HMAC), using this generated
    // combinded value is used to encrypt payload and header which creates digital signature
    //summary: combination of secret key and encoding algorithms is used to generate digital signature based on payload and headers.
    private static final String SECRET_KEY = "70337336763979244226452948404D635166546A576D5A7134743777217A2543";
    private String getSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte [] key = new byte[32];
        secureRandom.nextBytes(key);
        String keyString = Base64.getEncoder().encodeToString(key);
        return keyString;
    }

    //The subject claim in a JWT is meant to represent the entity that the JWT is about.
    //The value of the "sub" claim is a string or a URI that uniquely identifies the user or entity associated with the JWT.
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    //utility method that allows us to extract specific claim from a JWT token
    //by passing in a function that takes in a claims object and returns specific value
    //2 arguments. first is JWT token and a function that will extract a specific claim from token
    //it first calls extract AllClaims and extracts Claims
    //it then calls the apply method on the claimsResolver function, passing Claims Object
    //this will extract the specific claim.
    //finally it returns extracted claim.
    public <T> T extractClaim(String jwToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwToken);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    //Jwts is a class in java jwt library which is mainly used to generate JWSs by setting claims, header, signature using JWts.builder
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 *24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //the claims are user information that consists of info about token itself, as well as information
    //about the user or client that the token represents
    //Jwts provide parseBiilder() method to parse and verify the token by specifying the signing key using setSigningKey()
    //once the signing key is set, the parseClaimsJws() method can be used to parse the JWT to extract the claims
    //Jwts class provides a simple and easy to use API for generating parsing, signing, and verifying JWTs in java.
    private Claims extractAllClaims(String jwtToken) {
        JwtParser build = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build();
        return build
                .parseClaimsJws(jwtToken)
                .getBody();
    }


    //this method combines secret and encrypting algorithm
    //and generates combination of secret and encryption algorithm
    //which is presented using Keys.hmacShakKeyFor(keyBytes)
    //look at the name hmacShaKeyFor - hmac(encryption algorithm) + Sha(hashing algorithm, secret key)
    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
