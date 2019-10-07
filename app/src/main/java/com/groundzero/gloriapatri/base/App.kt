package com.groundzero.gloriapatri.base

import android.app.Application
import com.groundzero.gloriapatri.di.helper.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

  override fun onCreate() {
    super.onCreate()
    AppInjector.init(this)
  }
}