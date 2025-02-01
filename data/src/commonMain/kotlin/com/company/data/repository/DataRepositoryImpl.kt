package com.company.data.repository

import com.company.domain.repository.DataRepository

/**
 * @author maro
 * @since 2025. 1. 28.
 */
class DataRepositoryImpl : DataRepository {
    override suspend fun getName(): String {
        return "Hello World"
    }
}