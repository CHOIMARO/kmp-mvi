package com.company.kmp_test.store

import com.company.domain.usecase.GetNameUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * @author maro
 * @since 2025. 2. 1.
 */
data class TestState(
    val isLoading: Boolean = false,
    val getName: String = ""
): State {
    companion object {
        fun init() = TestState(getName = "", isLoading = false)
    }
}

sealed class TestAction : Action {
    data object OnClick: TestAction()
}

sealed class TestSideEffect: Effect {
    data object ShowErrorToast: TestSideEffect()
    data class ShowToast(val message: String) : TestSideEffect()
}

class TestStore(
    private val getNameUseCase: GetNameUseCase
) : Store<TestState, TestAction, TestSideEffect>() {
    override val _state: MutableStateFlow<TestState> = MutableStateFlow(initialState())

    override fun initialState(): TestState = TestState()

    override fun processAction(action: TestAction) {
        when (action) {
            TestAction.OnClick -> {
                scope.launch {
                    setState { copy(isLoading = true) }
                    val name = getNameUseCase()
                    setState { copy(getName = name, isLoading = false) }
                    postEffect(TestSideEffect.ShowToast("Success"))
                }
            }
        }
    }

}