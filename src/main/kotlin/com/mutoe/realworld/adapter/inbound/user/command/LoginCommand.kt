package com.mutoe.realworld.adapter.inbound.user.command

data class LoginCommand(
    val email: String,
    val password: String,
)
