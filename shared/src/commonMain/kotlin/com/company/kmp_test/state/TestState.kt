package com.company.kmp_test.state

import com.company.kmp_test.base.Intent
import com.company.kmp_test.base.SideEffect
import com.company.kmp_test.base.State

/**
 * @author maro
 * @since 2025. 1. 28.
 */
data class TestState(
    val isLoading: Boolean,
    val getName: String
): State() {
    companion object {
        fun init() = TestState(getName = "", isLoading = false)
    }
}

sealed class TestIntent : Intent() {
    data object OnClick: TestIntent()
}

sealed class TestSideEffect: SideEffect() {
    data object ShowErrorToast: TestSideEffect()
    data class ShowToast(val message: String) : TestSideEffect()
}