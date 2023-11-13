package albek.petprojects.touristapp.feature.home.di

import albek.petprojects.touristapp.feature.home.data.repository.HomeRepositoryImpl
import albek.petprojects.touristapp.feature.home.domain.repository.HomeRepository
import albek.petprojects.touristapp.network.retrofit.TouristAppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Provides
    fun provideRepository(apiService: TouristAppApiService): HomeRepository =
        HomeRepositoryImpl(apiService = apiService)
}