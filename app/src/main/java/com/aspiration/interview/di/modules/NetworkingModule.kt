package com.aspiration.interview.di.modules

import android.content.Context
import com.aspiration.interview.data.network.service.PostsService
import com.aspiration.interview.di.qualifiers.ApplicationContext
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkingModule(private val url: String) {
    companion object {
        private const val CONNECT_TIMEOUT = 30L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 1L

        private const val CACHE_SIZE = 10 * 1000 * 1000L // 10Mb
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(cacheFile: File) = Cache(cacheFile, CACHE_SIZE)

    @Provides
    @Singleton
    fun provideCacheFile(@ApplicationContext context: Context): File {
        val cacheFile = File(context.cacheDir, "okhttp_cache")
        cacheFile.mkdirs()

        return cacheFile
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.HOURS)
            .readTimeout(READ_TIMEOUT, TimeUnit.HOURS)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(gson: Gson, client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(PostsService::class.java)
}