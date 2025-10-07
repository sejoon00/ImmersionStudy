package com.project.immersionstudy.defaultModel.data

import com.project.immersionstudy.defaultModel.domain.Post
import com.project.immersionstudy.defaultModel.domain.PostQueryRepository
import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Repository

@Repository
class ExposedPostQueryRepository(): PostQueryRepository {
    override fun findBy(builder: (query: ExposedPostQueryBuilder) -> Unit) : List<Post> {
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
