package com.company.data.di


import com.company.data.repository.DataRepositoryImpl
import com.company.domain.repository.DataRepository
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

/**
 * @author maro
 * @since 2025. 1. 28.
 */
val localModule = module {
    singleOf(::DataRepositoryImpl).bind<DataRepository>()
}

val dataModule = module {
    includes(localModule)
}