package com.taxidriverhk.hkadbus2.activity.auth

import java.time.Instant

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.taxidriverhk.hkadbus2.exception.InternalErrorException

class Authenticator(private val authTokenSigner: Algorithm) {

    companion object {
        val TTL_SECONDS = 3600L
        val ISSUER = "taxidriverhk"
    }

    fun generateToken(): String {
        try {
            return JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(Instant.now().plusSeconds(TTL_SECONDS))
                .sign(authTokenSigner)
        } catch (exception: JWTCreationException) {
            throw InternalErrorException("Failed to generate auth token")
        }
    }

    fun verifyToken(token: String): Boolean {
        try {
            val verifier = JWT.require(authTokenSigner)
                // specify an specific claim validations
                .withIssuer(ISSUER)
                // reusable verifier instance
                .build()
            verifier.verify(token)
            return true
        } catch (exception: JWTVerificationException) {
            return false
        }
    }
}
