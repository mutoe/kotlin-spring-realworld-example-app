package com.mutoe.realworld.adapter.outbound.persistence

import com.mutoe.realworld.domain.user.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun getNextSequence(): Int {
        TODO("Not yet implemented")
    }
}
