package com.karimali.mazad.di

import com.karimali.mazad.data.api.ApiService
import com.karimali.mazad.domain.repository.MainRepository
import com.karimali.mazad.domain.repository.MainRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.annotation.Nullable

@Module
@InstallIn(ViewModelComponent::class)
object ReposModule {

    @Provides
    fun provideMainRepository(@Nullable service: ApiService) : MainRepository {
        return MainRepositoryImp(service)
    }
}