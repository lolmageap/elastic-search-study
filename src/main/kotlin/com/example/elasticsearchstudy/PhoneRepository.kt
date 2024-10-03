package com.example.elasticsearchstudy

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.data.jpa.repository.JpaRepository

interface PhoneRepository: JpaRepository<Phone, PhoneId> {
    fun findAllByNumberContainingIgnoreCase(
        number: String,
    ): List<Phone>
}

interface SearchPhoneRepository: ElasticsearchRepository<SearchPhone, PhoneId> {
    fun findAllByNumberContainingIgnoreCase(
        number: String,
    ): List<SearchPhone>
}