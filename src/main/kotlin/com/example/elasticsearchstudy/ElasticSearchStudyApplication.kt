package com.example.elasticsearchstudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ElasticSearchStudyApplication

fun main(args: Array<String>) {
    runApplication<ElasticSearchStudyApplication>(*args)
}
