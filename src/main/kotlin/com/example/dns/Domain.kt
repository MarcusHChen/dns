package com.example.dns
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("DOMAINS")
data class Domain(@Id val id: Int, val label: String, val ipv4: String, val parentId: Int?)
