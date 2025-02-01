package com.company.kmp_test.di

import com.company.data.di.dataModule
import com.company.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
/**
 * @author maro
 * @since 2025. 1. 24.
 */
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule, domainModule, dataModule)
    }
}