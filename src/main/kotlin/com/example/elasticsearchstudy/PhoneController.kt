package com.example.elasticsearchstudy

import org.springframework.web.bind.annotation.*

@RestController
class PhoneController(
    private val phoneService: PhoneService,
) {
    @PostMapping("/phone")
    fun save(
        @RequestBody phone: PhoneRequest,
    ) =
        phoneService.saveOne(phone)

    @GetMapping("/phone/{id}")
    fun findBy(
        @PathVariable id: PhoneId,
    ) =
        phoneService.findAllBy(id)

    @GetMapping("/phone")
    fun findAll() =
        phoneService.findAll()

    @DeleteMapping("/phone/{id}")
    fun deleteBy(
        @PathVariable id: PhoneId,
    ) =
        phoneService.deleteOneBy(id)
}