package com.mutoe.realworld.adapter.security.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
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
    @Value("\${conduit.jwt.secret}") private val secret: String,
    @Value("\${conduit.jwt.expiration}") private val expiration: String,
) {
    private val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun generateToken(userId: Int, email: String): String {
        return generateJws(userId, email)
    }

    fun validateToken(token: String): Boolean = !isInvalid(token)

    fun getUserIdFromToken(token: String): Int {
        return parseJws(token)!!.id.toInt()
    }

    fun getAuthentication(token: String): Authentication {
        val claims = parseJws(token)!!
        return UsernamePasswordAuthenticationToken(claims.id, token)
    }

    private fun generateJws(userId: Int, email: String): String {
        return Jwts.builder()
            .setId(userId.toString())
            .setSubject(email)
            .setExpiration(Date(System.currentTimeMillis() + expiration.toLong() * 60 * 1000))
            .setIssuedAt(Date())
            .signWith(key)
            .compact()
    }

    private fun parseJws(token: String): Claims? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: JwtException) {
            null
        }
    }

    private fun isInvalid(token: String): Boolean {
        return try {
            val claims = parseJws(token)
            claims!!.issuedAt.before(Date()) && isExpired(token)
        } catch (e: Exception) {
            false
        }
    }

    private fun isExpired(token: String): Boolean {
        return try {
            val claims = parseJws(token)
            claims!!.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}
