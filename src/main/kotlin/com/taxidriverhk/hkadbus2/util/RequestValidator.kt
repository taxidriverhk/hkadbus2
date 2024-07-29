package com.taxidriverhk.hkadbus2.util

import com.taxidriverhk.hkadbus2.exception.BadRequestException
import com.taxidriverhk.hkadbus2.model.domain.SortDirection

object RequestValidator {

    private val VALID_LANGUAGES = hashSetOf("en_us", "zh_hk")
    private val VALID_ORDER_BY = hashSetOf("username", "uploadedDate", "licensePlateNumber")

    @JvmStatic
    fun validateLanguage(language: String?) {
        if (!VALID_LANGUAGES.contains(language)) {
            throw BadRequestException("Invalid language ${language}")
        }
    }

    @JvmStatic
    fun validateOrderBy(orderBy: String?) {
        if (!VALID_ORDER_BY.contains(orderBy)) {
            throw BadRequestException("Order by is required and must be an attribute with unique data")
        }
    }

    @JvmStatic
    fun validateSort(sort: String?) {
        val entries = SortDirection.entries.map { it.name.lowercase() }
        entries.find { it == sort } ?: throw BadRequestException("Invalid sort direction ${sort}")
    }

    @JvmStatic
    fun validateSize(size: Int?) {
        if (size?.let { it < 1 } == true) {
            throw BadRequestException("Page size must be at least 1")
        }
    }
}
