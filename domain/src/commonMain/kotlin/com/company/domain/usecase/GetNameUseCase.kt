package com.company.domain.usecase

import com.company.domain.repository.DataRepository

/**
 * @author maro
 * @since 2025. 1. 28.
 */
class GetNameUseCase(
    private val dataRepository: DataRepository,
) {
    suspend operator fun invoke(): String = dataRepository.getName()
}