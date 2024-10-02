package com.example.elasticsearchstudy

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UseElasticSearchTest(
    private val phoneService: PhoneService,
    private val phoneRepository: PhoneRepository,
    private val searchPhoneRepository: SearchPhoneRepository,
): StringSpec({

    beforeTest {
        phoneRepository.deleteAllInBatch()
        searchPhoneRepository.deleteAll()
        TODO("phoneService.saveAll()")
    }

    "엘라스틱 서치를 사용해서 데이터를 조회하고 성능을 측정한다." {

    }
})