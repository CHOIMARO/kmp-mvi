package com.company.kmp_test.viewmodel

import com.company.kmp_test.base.BaseViewModel
import com.company.kmp_test.store.TestAction
import com.company.kmp_test.store.TestSideEffect
import com.company.kmp_test.store.TestState
import com.company.kmp_test.store.TestStore
import org.koin.core.component.get

/**
 * @author maro
 * @since 2025. 1. 28.
 */
class MainViewModel() : BaseViewModel<TestState, TestAction, TestSideEffect, TestStore>() {
    override val store: TestStore = get()
}