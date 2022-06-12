package com.example.dns

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DomainController(val service: DomainService) {
    @GetMapping("ipv4/{url}")
    fun getIpv4(@PathVariable url: String): String {
        val domain: Domain? = service findDomainFor url
        return domain?.ipv4 ?: throw ResourceNotFoundException()
    }
}