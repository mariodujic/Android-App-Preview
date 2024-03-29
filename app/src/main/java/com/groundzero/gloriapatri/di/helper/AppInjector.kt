package com.groundzero.gloriapatri.di.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.groundzero.gloriapatri.base.App
import com.groundzero.gloriapatri.di.components.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

class AppInjector {

  companion object {
    fun init(application: App) {
      DaggerAppComponent.builder().application(application).build().inject(application)
      application.registerActivityLifecycleCallbacks(object :
        Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(p0: Activity?) {
        }

        override fun onActivityResumed(p0: Activity?) {
        }

        override fun onActivityStarted(p0: Activity?) {
        }

        override fun onActivityDestroyed(p0: Activity?) {
        }

        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        }

        override fun onActivityStopped(p0: Activity?) {
        }

        override fun onActivityCreated(activity: Activity, p1: Bundle?) {
          handleInjection(activity)
        }
      })
    }

    private fun handleInjection(activity: Activity) {
      if (activity is HasAndroidInjector) {
        AndroidInjection.inject(activity)
      }
      if (activity is FragmentActivity) {
        activity.supportFragmentManager
          .registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
              fm: FragmentManager,
              f: Fragment,
              v: View,
              savedInstanceState: Bundle?
            ) {
              super.onFragmentViewCreated(fm, f, v, savedInstanceState)
              if (f is Injectable) {
                AndroidSupportInjection.inject(f)
              }
            }
          }, true)
      }
    }
  }
}