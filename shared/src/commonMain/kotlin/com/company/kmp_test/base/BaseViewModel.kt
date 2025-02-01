package com.company.kmp_test.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * @author maro
 * @since 2025. 1. 28.
 */

/**
 * 뷰의 현재 상태
 */
abstract class State

/**
 * 사용자의 액션
 */
abstract class Intent

/**
 * 화면 이동, 로깅, 에러메시지 등과 같은 SideEffect
 */
abstract class SideEffect

abstract class BaseViewModel<S: State, I: Intent, SE: SideEffect>(
    initialState: S
) : ViewModel() {
    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    val currentState: S
        get() = uiState.value

    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState: StateFlow<S>
        get() = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<I> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _sideEffect: Channel<SE> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        subscribeIntent()
    }
    /**
     * State 설정
     */
    fun reduce(reducer: S.() -> S) { _uiState.value = currentState.reducer() }

    /**
     * Intent 설정
     */
    fun postIntent(intent: I) = scope.launch { _intent.emit(intent) }

    /**
     * SideEffect 설정
     */
    fun postSideEffect(builder: () -> SE) = scope.launch { _sideEffect.send(builder()) }

    /**
     * Intent 구독
     */
    private fun subscribeIntent() = scope.launch {
        intent.collect { handleIntent(it) }
    }
    /**
     * Intent 핸들러
     */
    abstract fun handleIntent(intent: I)
}