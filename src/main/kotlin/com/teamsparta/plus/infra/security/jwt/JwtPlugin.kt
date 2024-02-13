package com.teamsparta.plus.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.sql.Date
import java.time.Instant

@Component
class JwtPlugin (
        @Value("\${auth.jwt.issuer}") private val issuer : String,
        @Value("\${auth.jwt.secret}") private val secret : String,
        @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour : Long
){

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(nickname: String): String{
      return generateToken(nickname, java.time.Duration.ofHours(accessTokenExpirationHour))
    }

    private fun generateToken(nickname:String, expirationPeriod: java.time.Duration): String {
        val claims:Claims = Jwts.claims()
                .add(mapOf("nickname" to nickname))
                .build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationPeriod)))
                .claims(claims)
                .signWith(key)
                .compact()
    }

}