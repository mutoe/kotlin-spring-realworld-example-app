package com.mutoe.realworld.adapter.outbound.user

import com.mutoe.realworld.domain.user.User
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "public", name = "user")
class UserPo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int,

    @Column(nullable = false, unique = true, length = 40)
    val email: String,

    @Column(nullable = false, unique = true, length = 20)
    val username: String,

    @Column(nullable = false, length = 64)
    val password: String,

    val bio: String? = null,

    val image: String? = null,

    @CreationTimestamp
    var createdAt: Instant,

    @UpdateTimestamp
    var updatedAt: Instant,
) {

    fun toDomain() = User(id, email, username, password, bio, image)

    companion object {
        fun User.toPo() = UserPo(id, email, username, password, bio, image, Instant.now(), Instant.now())
    }
}
