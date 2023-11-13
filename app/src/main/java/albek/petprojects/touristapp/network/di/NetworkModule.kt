package albek.petprojects.touristapp.network.di

import albek.petprojects.touristapp.network.retrofit.TouristAppApiService
import albek.petprojects.touristapp.network.retrofit.TouristAppClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideClient(): Retrofit = TouristAppClient.createClient()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TouristAppApiService =
        retrofit.create(TouristAppApiService::class.java)
}