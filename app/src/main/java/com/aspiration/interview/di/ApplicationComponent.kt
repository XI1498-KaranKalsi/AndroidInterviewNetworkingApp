package com.aspiration.interview.di

import com.aspiration.interview.di.modules.*
import com.aspiration.interview.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkingModule::class, DBModule::class, RxModule::class, ApplicationModule::class, PresenterModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}