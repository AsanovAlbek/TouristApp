package albek.petprojects.touristapp.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TouristAppClient {
    fun createClient(): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConst.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}