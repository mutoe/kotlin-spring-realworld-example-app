package com.mutoe.realworld.adapter.inbound.user.dto

import com.mutoe.realworld.adapter.inbound.user.command.LoginCommand

data class LoginDto(
    val email: String,
    val password: String,
) {
    fun toCommand() = LoginCommand(email, password)
}
