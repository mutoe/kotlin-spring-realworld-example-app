package com.mutoe.realworld.domain.user

import com.mutoe.realworld.adapter.inbound.user.command.RegisterCommand
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun register(command: RegisterCommand): Pair<User, String> {
        TODO("Not yet implemented")
    }
}
