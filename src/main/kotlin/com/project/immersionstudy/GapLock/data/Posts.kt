package com.project.immersionstudy.GapLock.data

import com.project.immersionstudy.GapLock.domain.Post
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.update

object Posts : LongIdTable(name = "post") {
    val content = text("content")
    val type = varchar("type", length = 50)
    val publisherId = long("publisher_id")
    val authorId = long("created_by")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
    val deletedAt = datetime("deleted_at").nullable()
}

// Insert
fun Posts.save(model: Post): Post {
    if (model.id == null) {
        val id = Posts.insertAndGetId {
            it[content] = model.content
            it[type] = model.type
            it[authorId] = model.authorId
            it[createdAt] = model.createdAt
            it[updatedAt] = model.updatedAt
            it[deletedAt] = model.deletedAt
        }.value
        return model.copy(id = id)
    } else {
        Posts.update {
            it[content] = model.content
            it[type] = model.type
            it[updatedAt] = model.updatedAt
            it[deletedAt] = model.deletedAt
        }
        return model
    }
}


fun ResultRow.toModel(): Post = Post(
    id = this[Posts.id].value,
    content = this[Posts.content],
    type = this[Posts.type],
    publisherId = this[Posts.publisherId],
    authorId = this[Posts.authorId],
    createdAt = this[Posts.createdAt],
    updatedAt = this[Posts.updatedAt],
    deletedAt = this[Posts.deletedAt]
)

fun Post.toDto(builder: UpdateBuilder<*>) {
    builder[Posts.content] = this.content
    builder[Posts.type] = this.type
    builder[Posts.authorId] = this.authorId
    builder[Posts.createdAt] = this.createdAt
    builder[Posts.updatedAt] = this.updatedAt
    builder[Posts.deletedAt] = this.deletedAt
}