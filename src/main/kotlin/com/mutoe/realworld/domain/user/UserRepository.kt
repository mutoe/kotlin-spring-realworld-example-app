package com.mutoe.realworld.domain.user

interface UserRepository {
    fun get(id: Int): User
    fun find(email: String): User?
    fun findByUsername(username: String): User?
    fun save(user: User): User
}
