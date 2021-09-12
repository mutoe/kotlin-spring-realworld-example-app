package com.mutoe.realworld.adapter.inbound.user.command

data class RegisterCommand(
    val email: String,
    val username: String,
    val password: String,
)
