package com.example.dns

import org.springframework.stereotype.Service

@Service
class DomainService(val db: DomainRepository) {

    fun findTopLevelDomains(): List<Domain> = db.findTopLevelDomains()

    infix fun findChildDomainsFor(parentId: Int): List<Domain> = db.findChildDomains(parentId)

    infix fun findChildrenOf(parentDomain: Domain?): List<Domain> {
        return parentDomain?.let {
            this findChildDomainsFor parentDomain.id
        } ?: run { emptyList<Domain>() }
    }

    infix fun findDomainFor(url: String): Domain? {
        val domainSegments: List<String> = url.split(".")
        val topLevelDomain: Domain? = findDomain(domainSegments[domainSegments.size - 1], findTopLevelDomains())
        var domain: Domain? = topLevelDomain
        var parentDomain: Domain? = topLevelDomain
        for (i in domainSegments.size - 1 downTo 1) {
            val childDomains: List<Domain> = this findChildrenOf parentDomain
            domain = findDomain(domainSegments[i - 1], childDomains)
            parentDomain = domain
        }
        return domain
    }

    fun findDomain(label: String, domains: List<Domain>): Domain? {
       return domains.firstOrNull() {it.label.equals(label, ignoreCase = true)}
    }
}