package com.mutoe.realworld.domain.user

data class User(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val bio: String?,
    val image: String?,
)
