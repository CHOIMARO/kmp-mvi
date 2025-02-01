package com.company.kmp_test.di

import com.company.kmp_test.viewmodel.MainViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * @author maro
 * @since 2025. 1. 24.
 */

expect val platformModule: Module

val sharedModule = module {
    viewModel { MainViewModel(get()) }
}