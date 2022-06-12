package com.example.dns

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class DomainServiceTest {
    private val mockDomainRepository: DomainRepository = mockk()
    private val domainService: DomainService = DomainService(mockDomainRepository)
    private val topLevelDomains: List<Domain> = mutableListOf(
        Domain(1, "com", "1.0.0.7", null),
        Domain(2, "org", "1.0.0.8", null)
    )

    @Test
    fun findChildrenOf() {
        val parentDomain: Domain = Domain(1, "com", "1.0.0.7", null)
        every { mockDomainRepository.findChildDomains(1) } answers {mutableListOf(
            Domain(11, "example", "1.0.7.1", 1),
            Domain(21, "test", "1.0.8.1", 1))}
        assertEquals(2, domainService.findChildrenOf(parentDomain).size)
    }

    @Test
    fun findChildrenOfEmpty() {
        val parentDomain: Domain = Domain(1, "com", "1.0.0.7", null)
        every { mockDomainRepository.findChildDomains(1) } answers {emptyList<Domain>()}
        assertEquals(0, domainService.findChildrenOf(parentDomain).size)
    }

    @Test
    fun findDomainFor() {
        val url = "about.test.example.com"
        every {mockDomainRepository.findTopLevelDomains()} answers {topLevelDomains}
        every { mockDomainRepository.findChildDomains(1) } answers {mutableListOf(
            Domain(11, "example", "1.0.7.1", 1),
            Domain(21, "test", "1.0.8.1", 1))}
        every { mockDomainRepository.findChildDomains(11) } answers {mutableListOf(
            Domain(211, "test", "1.9.8.1", 11),
            Domain(111, "about", "1.9.7.1", 11))}
        every { mockDomainRepository.findChildDomains(211) } answers {mutableListOf(
            Domain(2111, "about", "1.1.8.1", 211))}
        assertEquals("1.1.8.1", domainService.findDomainFor(url)?.ipv4 ?: "")
    }

    @Test
    fun findDomain() {
        val label = "ORG"
        assertEquals("1.0.0.8", domainService.findDomain(label, topLevelDomains)?.ipv4 ?: "")
    }

    @Test
    fun findDomainNotFound() {
        val label = "abc"
        assertNull(domainService.findDomain(label, topLevelDomains)?.ipv4 ?: null)
    }
}