package com.example.coroutineflow.hilt.usecase

import com.example.coroutineflow.data.domain.AppUseCase
import com.example.coroutineflow.data.domain.GetItemUseCase
import com.example.coroutineflow.data.remote.model.GetItemResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindGetItemUseCase(getItemUseCase: GetItemUseCase):AppUseCase<Void,GetItemResponse>
}