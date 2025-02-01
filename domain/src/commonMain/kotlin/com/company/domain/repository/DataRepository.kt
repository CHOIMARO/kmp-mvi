package com.company.domain.repository

/**
 * @author maro
 * @since 2025. 1. 28.
 */
interface DataRepository {
    suspend fun getName(): String
}