package com.example.elasticsearchstudy

import io.kotest.core.spec.style.StringSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import java.util.stream.IntStream
import kotlin.system.measureTimeMillis
import kotlin.time.measureTimedValue

@SpringBootTest
class UseElasticSearchTest(
    @Autowired private val jdbcTemplate: JdbcTemplate,
    private val phoneService: PhoneService,
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
                IntStream.range(0, 1_000_000)
                    .parallel()
                    .mapToObj { easyRandom.nextObject(Phone::class.java) }
                    .toList()
            }.apply {
                println("Posts generation time: $duration ms")
            }.value

        measureTimeMillis {
            phoneService.createBy(posts)
        }.apply {
            println("Insertion time: $this ms")
        }
    }

    "엘라스틱 서치를 사용해서 데이터를 조회하고 성능을 측정한다." {
        measureTimeMillis {
            phoneService.getAllBy("hello")
        }.apply {
            println("Search time: $this ms")
        }
    }
})