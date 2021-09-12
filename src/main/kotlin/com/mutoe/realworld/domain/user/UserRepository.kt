package com.mutoe.realworld.domain.user

interface UserRepository {
    fun getNextSequence(): Int
}
