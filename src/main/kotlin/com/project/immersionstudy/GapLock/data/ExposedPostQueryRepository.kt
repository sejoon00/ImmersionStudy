package com.project.immersionstudy.GapLock.data

import com.project.immersionstudy.GapLock.domain.Post
import org.jetbrains.exposed.sql.*

class ExposedPostQueryRepository() {
    fun findBy(builder: (query: ExposedPostQueryBuilder) -> Unit) : List<Post> {
        return ExposedPostQueryBuilder().apply(builder).build()
            .map { it.toModel() }
    }

}

class ExposedPostQueryBuilder {

    private val predicates = mutableListOf<SqlExpressionBuilder.() -> Op<Boolean>>()

    fun inIds(ids: List<Long>) {
        if(ids.isEmpty()) return
        this.predicates += { Posts.id inList ids }
    }

    internal fun build(): Query {

        val conditions = predicates.map { SqlExpressionBuilder.run(it) }
        val combinedCondition = when {
            conditions.isEmpty() -> Op.TRUE
            else -> conditions.reduce { acc, op -> acc and op }
        }

        return Posts
            .selectAll()
            .where {
                combinedCondition
            }
    }
}
