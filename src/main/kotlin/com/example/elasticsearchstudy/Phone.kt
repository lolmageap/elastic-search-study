package com.example.elasticsearchstudy

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.time.LocalDate

@Entity
data class Phone(
    val number: String,
    val author: String,
    val createdDate: LocalDate = LocalDate.now(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: PhoneId = PhoneId.NONE
}

@Document(indexName = "phone")
data class SearchPhone(
    @org.springframework.data.annotation.Id
    val id: PhoneId,
    val number: String,
    val author: String,
)

@JvmInline
value class PhoneId(
    val value: Long
) {
    companion object {
        val NONE = PhoneId(0)

        fun of(
            value: Long
        ) =
            PhoneId(value)
    }
}