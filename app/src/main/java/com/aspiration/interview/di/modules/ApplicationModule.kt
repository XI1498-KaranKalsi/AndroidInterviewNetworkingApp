package com.aspiration.interview.di.modules

import android.content.Context
import com.aspiration.interview.data.repositories.PostsRepositoryImpl
import com.aspiration.interview.data.db.PostDao
import com.aspiration.interview.data.network.service.PostsService
import com.aspiration.interview.di.qualifiers.ApplicationContext
import com.aspiration.interview.di.qualifiers.SchedulerIO
import com.aspiration.interview.di.qualifiers.SchedulerUI
import com.aspiration.interview.domain.GetPostsUseCase
import com.aspiration.interview.domain.GetPostsUseCaseImpl
import com.aspiration.interview.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext() = context

    @Provides
    fun provideRepository(api: PostsService, dao: PostDao): PostsRepository =
        PostsRepositoryImpl(api, dao)

    @Provides
    fun provideUsecase(
        repository: PostsRepository,
        @SchedulerIO scheduler: Scheduler,
        @SchedulerUI schedulerUI: Scheduler
    ): GetPostsUseCase = GetPostsUseCaseImpl(scheduler, schedulerUI, repository)
}