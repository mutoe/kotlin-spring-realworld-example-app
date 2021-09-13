package com.mutoe.realworld.domain.user

import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun register(email: String, username: String, password: String): User {
        if (userRepository.find(email) != null) throw EmailExistedException()
        if (userRepository.findByUsername(username) != null) throw UsernameExistedException()
        val encryptedPassword = passwordEncoder.encode(password)
        val user = User(0, email, username, encryptedPassword, null, null)
        return userRepository.save(user)
    }

    fun login(email: String, password: String): User {
        val user = userRepository.find(email) ?: throw EmailNotExistException()
        val matched = passwordEncoder.matches(password, user.password)
        if (!matched) throw BadCredentialsException("The email or password not matched.")
        return user
    }

    private class EmailExistedException : ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is existed")
    private class UsernameExistedException : ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is existed")
    private class EmailNotExistException : ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not exist")
}
