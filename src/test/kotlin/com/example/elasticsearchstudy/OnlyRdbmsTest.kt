package com.example.elasticsearchstudy

import io.kotest.core.spec.style.StringSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import java.util.stream.IntStream.range
import kotlin.system.measureTimeMillis
import kotlin.time.measureTimedValue

@SpringBootTest
class OnlyRdbmsTest(
    @Autowired private val jdbcTemplate: JdbcTemplate,
    @Autowired private val phoneRepository: PhoneRepository,
) : StringSpec({
    beforeEach {
        measureTimeMillis {
            jdbcTemplate.execute("DROP TABLE IF EXISTS phone")
            jdbcTemplate.execute(
                """
                CREATE TABLE phone (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    number VARCHAR(255) NOT NULL,
                    author VARCHAR(255) NOT NULL
                )
            """.trimIndent(),
            )
        }.apply {
            println("Table creation time: $this ms")
        }

        val easyRandom = PhoneFactory.create()

        val posts =
            measureTimedValue {
                range(0, 1_000_000)
                    .parallel()
                    .mapToObj { easyRandom.nextObject(Phone::class.java) }
                    .toList()
            }.apply {
                println("Posts generation time: $duration ms")
            }.value

        measureTimeMillis {
            jdbcTemplate.batchUpdate(
                """
                INSERT INTO phone (number, author)
                VALUES (?, ?)
            """.trimIndent(),
                posts.map { arrayOf(it.number, it.author) },
            )
        }.apply {
            println("Insertion time: $this ms")
        }
    }

    "오로지 RDBMS 만 사용해서 like 연산으로 데이터를 조회하고 성능을 측정한다." {
        measureTimeMillis {
            phoneRepository.findAllByNumberContainingIgnoreCase("hello")
        }.apply {
            println("Query time: $this ms")
        }
    }
})