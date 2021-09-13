package com.mutoe.realworld.adapter.outbound.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : JpaRepository<UserPo, Int> {
    fun findByEmail(email: String): UserPo?
    fun findByUsername(username: String): UserPo?
}
