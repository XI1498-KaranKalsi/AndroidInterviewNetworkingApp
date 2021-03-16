package com.aspiration.interview.di.modules

import com.aspiration.interview.presentation.MainAgreement
import com.aspiration.interview.presentation.MainPresenter
import dagger.Binds
import dagger.Module

@Module
interface PresenterModule {
    @Binds
    fun providePresenter(presenter: MainPresenter): MainAgreement.Presenter
}