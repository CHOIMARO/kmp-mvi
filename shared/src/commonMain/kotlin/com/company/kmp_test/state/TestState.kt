package com.company.kmp_test.state

import com.company.kmp_test.base.UiIntent
import com.company.kmp_test.base.UiSideEffect
import com.company.kmp_test.base.UiState

/**
 * @author maro
 * @since 2025. 1. 28.
 */
data class PostState(
    val isLoading: Boolean,
    val getName: String
): UiState() {
    companion object {
        fun init() = PostState(getName = "", isLoading = false)
    }
}

sealed class PostIntent : UiIntent() {
    data object OnClick: PostIntent()
}

sealed class PostSideEffect: UiSideEffect() {
    data object ShowErrorToast: PostSideEffect()
    data class ShowToast(val message: String) : PostSideEffect()
}