package com.mutoe.realworld.adapter.inbound.user

import com.mutoe.realworld.adapter.inbound.user.dto.LoginDto
import com.mutoe.realworld.adapter.inbound.user.dto.RegisterDto
import com.mutoe.realworld.adapter.inbound.user.dto.UserDto
import com.mutoe.realworld.adapter.inbound.user.dto.UserDto.Companion.toDto
import com.mutoe.realworld.adapter.security.utils.JwtTokenUtil
import com.mutoe.realworld.application.UserApplication
import io.jsonwebtoken.Claims
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val application: UserApplication,
    private val jwtTokenUtil: JwtTokenUtil,
) {

    @PostMapping("users")
    fun register(@RequestBody request: RegisterDto): UserDto {
        val user = application.register(request.toCommand())
        val token = jwtTokenUtil.generateToken(user.id, user.email)
        return user.toDto(token)
    }

    @PostMapping("users/login")
    fun login(@RequestBody request: LoginDto): UserDto {
        val user = application.login(request.toCommand())
        val token = jwtTokenUtil.generateToken(user.id, user.email)
        return user.toDto(token)
    }

    @PostMapping("users/logout")
    fun logout() {
        SecurityContextHolder.clearContext()
    }

    @GetMapping("user")
    fun getCurrent(@AuthenticationPrincipal principal: Claims): UserDto {
        val user = application.getCurrent(principal.id.toInt())
        val token = jwtTokenUtil.generateToken(user.id, user.email)
        return user.toDto(token)
    }
}
