package com.company.kmp_test.android

import android.app.Application
import com.company.kmp_test.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.KoinAppDeclaration
/**
 * @author maro
 * @since 2025. 1. 24.
 */
class KmpTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@KmpTestApplication)
        }
    }
}