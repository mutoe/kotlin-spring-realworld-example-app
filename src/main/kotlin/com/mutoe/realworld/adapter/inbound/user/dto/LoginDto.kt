package com.mutoe.realworld.adapter.inbound.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mutoe.realworld.adapter.inbound.user.command.LoginCommand

data class LoginDto(
    @JsonProperty("user")
    val user: LoginUserInfo,
) {
    data class LoginUserInfo(
        val email: String,
        val password: String,
    )

    fun toCommand() = LoginCommand(user.email, user.password)
}
