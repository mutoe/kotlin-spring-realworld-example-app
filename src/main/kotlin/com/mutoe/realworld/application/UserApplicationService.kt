package com.mutoe.realworld.application

import com.mutoe.realworld.adapter.inbound.user.command.RegisterCommand
import com.mutoe.realworld.domain.user.User
import com.mutoe.realworld.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class UserApplicationService(
    private val userService: UserService,
) {
    fun register(command: RegisterCommand): Pair<User, String> = userService.register(command)
}
