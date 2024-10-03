package com.example.elasticsearchstudy

import org.springframework.web.bind.annotation.*

@RestController
class PhoneController(
    private val phoneService: PhoneService,
) {
    @PostMapping("/phone")
    fun post(
        @RequestBody phone: PhoneRequest,
    ) =
        phoneService.createBy(phone)

    @GetMapping("/phone/{id}")
    fun get(
        @PathVariable id: PhoneId,
    ) =
        phoneService[id]

    @GetMapping("/phone")
    fun get() =
        phoneService.getAll()

    @DeleteMapping("/phone/{id}")
    fun delete(
        @PathVariable id: PhoneId,
    ) =
        phoneService.removeBy(id)
}