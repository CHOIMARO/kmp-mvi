package com.company.kmp_test.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.company.domain.usecase.GetNameUseCase
import com.company.kmp_test.base.BaseViewModel
import com.company.kmp_test.state.PostIntent
import com.company.kmp_test.state.PostSideEffect
import com.company.kmp_test.state.PostState
import kotlinx.coroutines.launch

/**
 * @author maro
 * @since 2025. 1. 28.
 */
class MainViewModel(
    private val getNameUseCase: GetNameUseCase
) : BaseViewModel<PostState, PostIntent, PostSideEffect>(
    PostState.init()
) {
    override fun handleIntent(intent: PostIntent) {
        viewModelScope.launch {
            when(intent) {
                PostIntent.OnClick -> {
                    reduce { copy(isLoading = true) }
                    try {
                        val name = getNameUseCase()
                        reduce { copy(getName = name, isLoading = false) }
                        postSideEffect { PostSideEffect.ShowToast("Success") }
                    } catch(e: Exception) {
                        postSideEffect { PostSideEffect.ShowErrorToast }
                        reduce { copy(isLoading = false) }
                    }
                }
            }
        }
    }
}