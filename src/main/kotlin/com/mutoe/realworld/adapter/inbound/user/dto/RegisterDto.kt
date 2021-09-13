package com.mutoe.realworld.adapter.inbound.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mutoe.realworld.adapter.inbound.user.command.RegisterCommand

data class RegisterDto(
    @JsonProperty("user")
    val user: RegisterUserInfo,
) {
    data class RegisterUserInfo(
        val email: String,
        val username: String,
        val password: String,
    )

    fun toCommand() = RegisterCommand(user.email, user.username, user.password)
}
