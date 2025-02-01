package com.company.kmp_test.store

import com.company.domain.usecase.GetNameUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * @author maro
 * @since 2025. 2. 2.
 */
object StoreFactory: KoinComponent {
    fun makeTestStore() = TestStore (
        getNameUseCase = get<GetNameUseCase>()
    )
}