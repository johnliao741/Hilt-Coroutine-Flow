package com.example.coroutineflow.hilt.usecase

import com.example.coroutineflow.util.StringToIntAdapter
import com.example.coroutineflow.util.StringToNullIntAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JsonModule {

    @Singleton
    @Provides
    fun provideStringToIntAdapter(): StringToIntAdapter = StringToIntAdapter()

    @Singleton
    @Provides
    fun provideStringToNullIntAdapter(): StringToNullIntAdapter = StringToNullIntAdapter()

    @Singleton
    @Provides
    fun provideMoshi(
        stringToIntAdapter: StringToIntAdapter,
        stringToNullIntAdapter: StringToNullIntAdapter
    ): Moshi = Moshi.Builder()
        .add(stringToIntAdapter)
        .add(stringToNullIntAdapter)
        .build()
}