package com.project.immersionstudy.exposed.create

import com.project.immersionstudy.defaultModel.data.Posts
import com.project.immersionstudy.defaultModel.data.toModel
import com.project.immersionstudy.defaultModel.domain.Post
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.engine.concurrency.TestExecutionMode
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("h2")
class ExposedInsert : DescribeSpec({

    val model = Post.create(
        content = "content",
        type = "type",
        publisherId = 1L,
        authorId = 1L
    )

    testExecutionMode = TestExecutionMode.Concurrent

    beforeSpec {
        Database.connect(
            url = "jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
            driver = "org.h2.Driver",
            user = "sa",
            password = ""
        )
    }

    beforeEach {
        transaction { SchemaUtils.create(Posts) }
    }

    afterEach {
        transaction { SchemaUtils.drop(Posts) }
    }

    describe("LongIdTable - 게시글 단일 삽입 테스트") {
        it("id가 없어도 단일 삽입에 성공한다.") {
            val insertedPost = transaction {
                Posts.insert {
                    it[content] = model.content
                    it[type] = model.type
                    it[publisherId] = model.publisherId
                    it[authorId] = model.authorId
                    it[createdAt] = model.createdAt
                    it[updatedAt] = model.updatedAt
                    it[deletedAt] = model.deletedAt
                }
            }

            val selectAll = transaction {
                Posts.selectAll()
                    .map { it.toModel() }
            }

            selectAll.size shouldBe 1
            selectAll.first() shouldBe model.copy(id = insertedPost[Posts.id].value)
        }

        it("id를 지정해서 삽입할 수 있다.") {
            val insertedPost = transaction {
                Posts.insert {
                    it[id] = 2L
                    it[content] = model.content
                    it[type] = model.type
                    it[publisherId] = model.publisherId
                    it[authorId] = model.authorId
                    it[createdAt] = model.createdAt
                    it[updatedAt] = model.updatedAt
                    it[deletedAt] = model.deletedAt
                }
            }

            val selectAll = transaction {
                Posts.selectAll()
                    .map { it.toModel() }
            }

            selectAll.size shouldBe 1
            selectAll.first() shouldBe model.copy(id = insertedPost[Posts.id].value)
        }

        it("id가 겹치면 예외가 발생한다.") {
            transaction {
                Posts.insert {
                    it[content] = model.content
                    it[type] = model.type
                    it[publisherId] = model.publisherId
                    it[authorId] = model.authorId
                    it[createdAt] = model.createdAt
                    it[updatedAt] = model.updatedAt
                    it[deletedAt] = model.deletedAt
                }
            }

            shouldThrow<ExposedSQLException> {
                transaction {
                    Posts.insert {
                        it[id] = 1L
                        it[content] = model.content
                        it[type] = model.type
                        it[publisherId] = model.publisherId
                        it[authorId] = model.authorId
                        it[createdAt] = model.createdAt
                        it[updatedAt] = model.updatedAt
                        it[deletedAt] = model.deletedAt
                    }
                }
            }
        }

        /**
         * insertIgnore 왜 필요할까
         * 이미 동일한 데이터가 존재할 때 중복 저장을 방지하고 로직을 계속 실행?
         * 로직을 계속 실행할 필요가 있을까 실패처리가 명확하지 않나
         */
        it("insertIgnore를 사용하면 예외가 무시된다.") {

            transaction {
                Posts.insert {
                    it[id] = 1L
                    it[content] = model.content
                    it[type] = model.type
                    it[publisherId] = model.publisherId
                    it[authorId] = model.authorId
                    it[createdAt] = model.createdAt
                    it[updatedAt] = model.updatedAt
                    it[deletedAt] = model.deletedAt
                }
            }

            shouldNotThrow<ExposedSQLException> {
                transaction {
                    Posts.insertIgnore {
                        it[id] = 1L
                        it[content] = model.content
                        it[type] = model.type
                        it[publisherId] = model.publisherId

                        it[authorId] = model.authorId
                        it[createdAt] = model.createdAt
                        it[updatedAt] = model.updatedAt
                        it[deletedAt] = model.deletedAt
                    }
                }
            }

             transaction {
                Posts.selectAll()
                    .count()
            } shouldBe 1
        }
    }

    describe("동시성 테스트") {
        it("두 개의 row가 동시에 삽입되어도 정상 삽입된다.") {

        }
    }
})
