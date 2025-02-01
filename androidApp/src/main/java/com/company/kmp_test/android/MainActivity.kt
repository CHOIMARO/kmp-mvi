package com.company.kmp_test.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.company.kmp_test.viewmodel.MainViewModel
import com.company.ui.App
import com.company.ui.screen.HomeScreen
import com.company.ui.theme.MyApplicationTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel = getViewModel()
        setContent {
            App(viewModel)
        }
    }
}
