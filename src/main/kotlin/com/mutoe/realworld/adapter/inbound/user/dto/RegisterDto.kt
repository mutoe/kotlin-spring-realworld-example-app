package com.mutoe.realworld.adapter.inbound.user.dto

import com.mutoe.realworld.adapter.inbound.user.command.RegisterCommand

data class RegisterDto(
    val email: String,
    val username: String,
    val password: String,
) {
    fun toCommand() = RegisterCommand(email, username, password)
}
