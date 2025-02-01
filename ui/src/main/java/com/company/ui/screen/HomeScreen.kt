package com.company.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.company.kmp_test.state.PostIntent
import com.company.kmp_test.state.PostSideEffect
import com.company.kmp_test.viewmodel.MainViewModel

/**
 * @author maro
 * @since 2025. 1. 24.
 */

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when(effect) {
                PostSideEffect.ShowErrorToast -> Log.e(">>>>>", "ERROR")
                is PostSideEffect.ShowToast -> Log.e(">>>>>", effect.message)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    viewModel.handleIntent(PostIntent.OnClick)
                }) {
                    Text("Click me")
                }
                Spacer(modifier = Modifier.height(16.dp))  // 버튼과 텍스트 사이 간격
                Text(state.getName)  // 가져온 값을 표시
            }
        }
    }
}