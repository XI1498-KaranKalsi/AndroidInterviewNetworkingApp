package com.aspiration.interview.di.modules

import android.content.Context
import com.aspiration.interview.data.db.AppDatabase
import com.aspiration.interview.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DBModule() {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Provides
    fun providePostDao(db: AppDatabase) = db.postsDao()

}