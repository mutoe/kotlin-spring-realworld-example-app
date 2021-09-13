package com.mutoe.realworld.adapter.outbound.user

import com.mutoe.realworld.adapter.outbound.user.UserPo.Companion.toPo
import com.mutoe.realworld.domain.user.User
import com.mutoe.realworld.domain.user.UserRepository
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
    private val dao: UserDao,
) : UserRepository {
    override fun get(id: Int): User {
        return dao.getById(id).toDomain()
    }

    override fun find(email: String): User? {
        return dao.findByEmail(email)?.toDomain()
    }

    override fun findByUsername(username: String): User? {
        return dao.findByUsername(username)?.toDomain()
    }

    override fun save(user: User): User {
        return dao.save(user.toPo()).toDomain()
    }
}
