package com.company.kmp_test.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author maro
 * @since 2025. 1. 29.
 */

/**
 * 뷰의 현재 상태
 */
interface State

/**
 * 사용자의 액션
 */
interface Action

/**
 * 화면 이동, 로깅, 에러메시지 등과 같은 SideEffect
 */
interface Effect

abstract class Store<S : State, A : Action, E: Effect> {
    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    // State management
    protected abstract val _state: MutableStateFlow<S>
    private val state: StateFlow<S> get() = _state.asStateFlow()
    protected val currentState: S get() = state.value

    // Side effects
    protected val _effect = MutableSharedFlow<E>()
    private val effect: SharedFlow<E> = _effect.asSharedFlow()

    // Abstract functions that must be implemented by concrete stores
    protected abstract fun initialState(): S
    protected abstract fun processAction(action: A)

    /**
     * Dispatch an action to the store
     */
    fun dispatch(action: A) {
        scope.launch {
            processAction(action)
        }
    }

    /**
     * Observe state changes
     */
    fun observeState(): StateFlow<S> = state

    /**
     * Observe side effects
     */
    fun observeEffect(): SharedFlow<E> = effect

    /**
     * Post a new state
     */
    protected fun setState(reduce: S.() -> S) {
        val newState = currentState.reduce()
        _state.value = newState
    }

    /**
     * Post a side effect
     */
    protected suspend fun postEffect(effect: E) {
        _effect.emit(effect)
    }

    /**
     * Clean up resources when the store is no longer needed
     */
    fun clear() {
        job.cancel()
    }

    /**
     * Helper function to handle errors
     */
    protected suspend fun <T> runCatching(
        action: suspend () -> T,
        onSuccess: suspend (T) -> Unit,
        onError: suspend (Throwable) -> Unit
    ) {
        try {
            val result = action()
            onSuccess(result)
        } catch (e: Throwable) {
            onError(e)
        }
    }

}