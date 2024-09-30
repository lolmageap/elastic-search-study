package com.example.elasticsearchstudy

data class PhoneRequest(
    val number: String,
    val author: String,
) {
    fun toPhone() =
        Phone(
            number = number,
            author = author,
        )
}