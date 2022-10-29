package com.bridgelabz.userloginjwt.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {
    private static final String TOKEN_SECRET = "Abhishek";

    public String createToken(Long id) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create().withClaim("userId", id).sign(algorithm);
        return token;
    }

    public Long decodeToken(String token){
        Long userId;
        Verification verification = null;
        verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtverifier=verification.build();
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        Claim claim=decodedjwt.getClaim("userId");
        userId=claim.asLong();
        return userId;
    }
}

/*@Component is an annotation that allows Spring to automatically detect our custom beans.

In other words, without having to write any explicit code, Spring will:

Scan our application for classes annotated with @Component
Instantiate them and inject any specified dependencies into them
Inject them wherever needed*/