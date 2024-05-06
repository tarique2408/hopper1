package com.hopper.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hopper.entity.PropertyUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTService {
    //to generate jwt we should have to four things
    //1.secret key
    //2.issuer
    //3.expiry time
    //4.algorithm that is used to generate jwt
    @Value("${jwt.secret.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry}")
    private int expiryAt;

    private Algorithm algorithm;
    @PostConstruct
    public void postConstruct(){
        algorithm=Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(PropertyUser user){
       return JWT.create().
                withClaim("USER_NAME",user.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryAt))
                .sign(algorithm);
    }


}
