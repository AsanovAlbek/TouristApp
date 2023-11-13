package albek.petprojects.touristapp.feature.detail.di

import albek.petprojects.touristapp.feature.detail.data.repository.DetailRepositoryImpl
import albek.petprojects.touristapp.feature.detail.domain.repository.DetailRepository
import albek.petprojects.touristapp.network.retrofit.TouristAppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DetailModule {
    @Provides
    fun provideDetailRepository(apiService: TouristAppApiService): DetailRepository =
        DetailRepositoryImpl(apiService = apiService)
}