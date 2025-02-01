package com.company.kmp_test.viewmodel

import androidx.lifecycle.viewModelScope
import com.company.domain.usecase.GetNameUseCase
import com.company.kmp_test.base.BaseViewModel
import com.company.kmp_test.state.TestIntent
import com.company.kmp_test.state.TestSideEffect
import com.company.kmp_test.state.TestState
import kotlinx.coroutines.launch

/**
 * @author maro
 * @since 2025. 1. 28.
 */
class MainViewModel(
    private val getNameUseCase: GetNameUseCase
) : BaseViewModel<TestState, TestIntent, TestSideEffect>(
    TestState.init()
) {
    override fun handleIntent(intent: TestIntent) {
        scope.launch {
            when(intent) {
                TestIntent.OnClick -> {
                    reduce { copy(isLoading = true) }
                    try {
                        val name = getNameUseCase()
                        reduce { copy(getName = name, isLoading = false) }
                        postSideEffect { TestSideEffect.ShowToast("Success") }
                    } catch(e: Exception) {
                        reduce { copy(isLoading = false) }
                        postSideEffect { TestSideEffect.ShowErrorToast }
                    }
                }
            }
        }
    }
}