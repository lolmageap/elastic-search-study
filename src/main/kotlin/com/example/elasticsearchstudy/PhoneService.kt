package com.example.elasticsearchstudy

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PhoneService(
    private val phoneRepository: PhoneRepository,
    private val searchPhoneRepository: SearchPhoneRepository,
) {
    fun saveOne(
        request: PhoneRequest,
    ) {
        val phone = phoneRepository.save(
            request.toPhone(),
        )

        val searchPhone = SearchPhone(
            id = phone.id,
            number = phone.number,
            author = phone.author,
        )

        searchPhoneRepository.save(searchPhone)
    }

    fun findOneBy(
        number: String,
    ) =
        searchPhoneRepository.findByNumber(number)
            ?: throw IllegalArgumentException("Phone not found by number: $number")

    fun findOneBy(
        id: PhoneId,
    ) =
        phoneRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Phone not found by id: $id")

    fun findAll() =
        phoneRepository.findAll().toList()

    fun findAllBy(
        pageable: Pageable,
    ) =
        phoneRepository.findAll(pageable)

    fun deleteOneBy(
        id: PhoneId,
    ) =
        phoneRepository.deleteById(id)
}