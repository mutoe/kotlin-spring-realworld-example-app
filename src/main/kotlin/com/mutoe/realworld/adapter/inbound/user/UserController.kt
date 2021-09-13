package com.mutoe.realworld.adapter.inbound.user

import com.mutoe.realworld.adapter.inbound.user.dto.LoginDto
import com.mutoe.realworld.adapter.inbound.user.dto.RegisterDto
import com.mutoe.realworld.adapter.inbound.user.dto.UserDto
import com.mutoe.realworld.adapter.inbound.user.dto.UserDto.Companion.toDto
import com.mutoe.realworld.adapter.security.utils.JwtTokenUtil
import com.mutoe.realworld.application.AuthApplication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private val authApplication: AuthApplication,
    private val jwtTokenUtil: JwtTokenUtil,
) {

    @PostMapping
    fun register(@RequestBody request: RegisterDto): UserDto {
        val user = authApplication.register(request.toCommand())
        val token = jwtTokenUtil.generateToken(user.id, user.email)
        return user.toDto(token)
    }

    @PostMapping("login")
    fun login(@RequestBody request: LoginDto): UserDto {
        val user = authApplication.login(request.toCommand())
        val token = jwtTokenUtil.generateToken(user.id, user.email)
        return user.toDto(token)
    }

    @PostMapping("logout")
    fun logout() {
        SecurityContextHolder.clearContext()
    }
}
