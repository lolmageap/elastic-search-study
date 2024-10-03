package com.example.elasticsearchstudy

import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.FieldPredicates.*
import org.jeasy.random.randomizers.range.LocalDateRangeRandomizer
import org.jeasy.random.randomizers.range.LongRangeRandomizer
import org.jeasy.random.randomizers.text.StringRandomizer
import org.springframework.data.mapping.toDotPath
import java.time.LocalDate

object PhoneFactory {
    fun create() = EasyRandom(easyRandomParameters)

    fun create(
        number: String,
        start: LocalDate,
        end: LocalDate,
    ): Phone {
        val parameter = easyRandomParameters
        parameter
            .randomize(numberPredicate) { number }
            .randomize(authorPredicate, StringRandomizer(1, 100))
            .randomize(createdDatePredicate, LocalDateRangeRandomizer(start, end))
        return EasyRandom(parameter).nextObject(Phone::class.java)
    }

    fun create(
        number: String,
        author: String,
        start: LocalDate,
        end: LocalDate,
    ): EasyRandom {
        val idPredicate =
            named("id")
                .and(ofType(Long::class.java))
                .and(inClass(Phone::class.java))

        val numberPredicate =
            named("number")
                .and(ofType(Long::class.java))
                .and(inClass(Phone::class.java))

        val authorPredicate =
            named("author")
                .and(ofType(String::class.java))
                .and(inClass(Phone::class.java))

        val param =
            EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(start, end)
                .randomize(numberPredicate) { number }
                .randomize(authorPredicate) { author }

        return EasyRandom(param)
    }

    private val easyRandomParameters
        get() =
            EasyRandomParameters()
                .excludeField(named("id"))
                .stringLengthRange(1, 100)
                .randomize(Long::class.java, LongRangeRandomizer(1L, 100_000L))
                .dateRange(
                    LocalDate.of(1999, 2, 11),
                    LocalDate.of(2024, 12, 31),
                )

    private val numberPredicate
        get() =
            named(Phone::number.toDotPath())
                .and(ofType(String::class.java))
                .and(inClass(Phone::class.java))

    private val authorPredicate
        get() =
            named(Phone::author.toDotPath())
                .and(ofType(String::class.java))
                .and(inClass(Phone::class.java))

    private val createdDatePredicate
        get() =
            named(Phone::createdDate.toDotPath())
                .and(ofType(LocalDate::class.java))
                .and(inClass(Phone::class.java))
}