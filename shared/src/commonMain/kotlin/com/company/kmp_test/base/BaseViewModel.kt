package com.company.kmp_test.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.kmp_test.store.Action
import com.company.kmp_test.store.Effect
import com.company.kmp_test.store.State
import com.company.kmp_test.store.Store
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * @author maro
 * @since 2025. 1. 28.
 */

abstract class BaseViewModel<S: State, A : Action, E: Effect, ST : Store<S, A, E>>() : ViewModel(), KoinComponent {
    protected abstract val store: ST

    fun observeState(): StateFlow<S> = store.observeState()
    fun observeEffect(): SharedFlow<E> = store.observeEffect()
    fun dispatch(action: A): Unit = store.dispatch(action)

    override fun onCleared() {
        super.onCleared()
        store.clear()
    }
}