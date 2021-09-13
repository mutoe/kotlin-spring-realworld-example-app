package com.mutoe.realworld.adapter.inbound.user.dto

import com.mutoe.realworld.domain.user.User
import org.springframework.web.bind.annotation.ResponseBody

data class UserInfo(
    val email: String,
    val username: String,
    val bio: String?,
    val image: String?,
    val token: String,
)

@ResponseBody
data class UserDto(
    val user: UserInfo,
) {
    companion object {
        fun User.toDto(token: String) = UserDto(
            UserInfo(email, username, bio, image, token)
        )
    }
}
