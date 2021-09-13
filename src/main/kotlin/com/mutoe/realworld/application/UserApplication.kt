package com.mutoe.realworld.application

import com.mutoe.realworld.adapter.inbound.user.command.LoginCommand
import com.mutoe.realworld.adapter.inbound.user.command.RegisterCommand
import com.mutoe.realworld.domain.user.User
import com.mutoe.realworld.domain.user.UserRepository
import com.mutoe.realworld.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class UserApplication(
    private val userService: UserService,
    private val userRepository: UserRepository,
) {
    fun register(command: RegisterCommand): User =
        userService.register(command.email, command.username, command.password)

    fun login(command: LoginCommand): User =
        userService.login(command.email, command.password)

    fun getCurrent(id: Int): User = userRepository.get(id)
}
