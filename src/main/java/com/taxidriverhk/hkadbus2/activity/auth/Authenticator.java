package com.taxidriverhk.hkadbus2.activity.auth;

import javax.inject.Inject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.taxidriverhk.hkadbus2.exception.InternalErrorException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class Authenticator {

    private static final String ISSUER = "taxidriverhk";

    private final Algorithm authTokenSigner;
    
    public String generateToken() {
        try {
            return JWT.create()
                .withIssuer(ISSUER)
                .sign(authTokenSigner);
        } catch (final JWTCreationException exception){
            throw new InternalErrorException("Failed to generate auth token");
        }
    }

    public boolean verifyToken(final String token) {
        try {
            final JWTVerifier verifier = JWT.require(authTokenSigner)
                // specify an specific claim validations
                .withIssuer(ISSUER)
                // reusable verifier instance
                .build();
                
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }
}
