package com.example.dns

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DnsApplication

fun main(args: Array<String>) {
	runApplication<DnsApplication>(*args)
}
