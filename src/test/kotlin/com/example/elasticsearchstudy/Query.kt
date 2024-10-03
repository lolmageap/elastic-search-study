package com.example.elasticsearchstudy

fun insertPhoneQuery() =
    """
        INSERT INTO phone (number, author)
        VALUES (?, ?)
    """.trimIndent()

fun insertPhoneQuery(
    number: String,
    author: String,
) =
    """
        INSERT INTO phone (number, author)
        VALUES ('$number', '$author')
    """.trimIndent()