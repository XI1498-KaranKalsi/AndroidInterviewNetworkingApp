package com.aspiration.interview.di.modules

import com.aspiration.interview.di.qualifiers.SchedulerIO
import com.aspiration.interview.di.qualifiers.SchedulerUI
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


@Module
class RxModule {
    @Singleton
    @SchedulerIO
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Singleton
    @SchedulerUI
    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
