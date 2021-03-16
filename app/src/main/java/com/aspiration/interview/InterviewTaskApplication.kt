package com.aspiration.interview

import android.app.Application
import com.aspiration.interview.di.ApplicationComponent
import com.aspiration.interview.di.DaggerApplicationComponent
import com.aspiration.interview.di.modules.ApplicationModule
import com.aspiration.interview.di.modules.NetworkingModule

class InterviewTaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkingModule(NetworkingModule("https://jsonplaceholder.typicode.com"))
            .build()
    }

    companion object {
        var applicationComponent: ApplicationComponent? = null
    }
}