package com.example.coroutineflow.hilt

import com.example.coroutineflow.data.respository.MyRepository
import com.example.coroutineflow.data.service.MyService
import com.example.coroutineflow.hilt.usecase.JsonModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [JsonModule::class])
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    fun provideMyService(retrofit: Retrofit): MyService =
        retrofit.create(MyService::class.java)
    @Singleton
    @Provides
    fun provideMyRepository(myService: MyService): MyRepository = MyRepository(myService)

}