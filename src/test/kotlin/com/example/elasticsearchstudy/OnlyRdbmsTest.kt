package com.example.elasticsearchstudy

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OnlyRdbmsTest(
    private val phoneRepository: PhoneRepository,
): StringSpec({
    beforeEach {
        phoneRepository.deleteAllInBatch()
        TODO("phoneRepository.saveAll()")
    }

    "오로지 RDBMS 만 사용해서 데이터를 조회하고 성능을 측정한다." {

    }
})