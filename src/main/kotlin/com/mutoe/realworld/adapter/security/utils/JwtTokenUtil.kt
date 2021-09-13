package com.mutoe.realworld.adapter.security.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenUtil(
    @Value("\${conduit.jwt.expiration}") private val expiration: String,
) {
    private val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun generateToken(userId: Int, email: String): String {
        return generateJws(userId, email)
    }

    fun validateToken(token: String): Boolean = !isInvalid(token)

    fun getAuthentication(token: String): Authentication {
        val claims = parseJws(token)
        return UsernamePasswordAuthenticationToken(claims, token, listOf())
    }

    private fun generateJws(userId: Int, email: String): String {
        return Jwts.builder()
            .setIssuer("conduit")
            .setAudience(email)
            .setId(userId.toString())
            .setSubject(email)
            .setNotBefore(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration.toLong() * 60 * 1000))
            .setIssuedAt(Date())
            .signWith(key)
            .compact()
    }

    private fun parseJws(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isInvalid(token: String): Boolean {
        return try {
            val claims = parseJws(token)
            claims.notBefore.before(Date()) && isExpired(token)
        } catch (e: Exception) {
            true
        }
    }

    private fun isExpired(token: String): Boolean {
        return try {
            val claims = parseJws(token)
            claims.expiration.before(Date())
        } catch (e: Exception) {
            true
        }
    }
}
