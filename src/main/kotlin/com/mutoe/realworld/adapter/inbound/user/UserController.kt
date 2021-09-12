package com.mutoe.realworld.adapter.inbound.user

import com.mutoe.realworld.adapter.inbound.user.dto.RegisterDto
import com.mutoe.realworld.adapter.inbound.user.dto.UserDto
import com.mutoe.realworld.adapter.inbound.user.dto.UserDto.Companion.toDto
import com.mutoe.realworld.application.UserApplicationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(
    private val userApplicationService: UserApplicationService,
) {

    @PostMapping("register")
    fun register(request: RegisterDto): UserDto {
        val (user, token) = userApplicationService.register(request.toCommand())
        return user.toDto(token)
    }
}
