package com.company.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.company.kmp_test.viewmodel.MainViewModel
import com.company.ui.screen.HomeScreen
import com.company.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

/**
 * @author maro
 * @since 2025. 1. 24.
 */

@Composable
fun App() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}