package com.example.elasticsearchstudy

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.data.jpa.repository.JpaRepository

interface PhoneRepository: JpaRepository<Phone, PhoneId>

interface SearchPhoneRepository: ElasticsearchRepository<SearchPhone, PhoneId> {
    fun findByNumber(
        number: String,
    ): SearchPhone?
}