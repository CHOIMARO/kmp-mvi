package com.company.domain.di

import com.company.domain.usecase.GetNameUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * @author maro
 * @since 2025. 1. 28.
 */
val domainModule = module {
    singleOf(::GetNameUseCase)
}