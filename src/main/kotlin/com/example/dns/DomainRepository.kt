package com.example.dns

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface DomainRepository : CrudRepository<Domain, Int> {
    @Query("SELECT * FROM DOMAINS WHERE parentId is null")
    fun findTopLevelDomains(): List<Domain>

    @Query("SELECT * FROM DOMAINS WHERE parentId = :parentId")
    fun findChildDomains(parentId: Int): List<Domain>
}