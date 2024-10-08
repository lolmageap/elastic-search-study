package com.example.elasticsearchstudy

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "elasticsearch")
data class ElasticSearchProperty(
    private val host: String,
    private val port: Int
) {
    val uri: String
        get() = "$host:$port"
}