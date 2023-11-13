package albek.petprojects.touristapp.network.retrofit

import albek.petprojects.touristapp.network.model.RemoteModelConst
import albek.petprojects.touristapp.network.model.detail.BlogDetailResponse
import albek.petprojects.touristapp.network.model.detail.BlogPreviewResponse
import albek.petprojects.touristapp.network.model.main.MainContentResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TouristAppApiService {
    @GET("main")
    suspend fun getMainPage(
        @Query("id") mainId: Int = RemoteModelConst.MAIN_ID
    ): MainContentResponse

    @GET("blog")
    suspend fun getBlogPreviews(
        @Query("id") mainId: Int = RemoteModelConst.MAIN_ID,
        @Query("format") previewFormat: String = RemoteModelConst.CARD_FORMAT
    ): BlogPreviewResponse

    @GET("blog-info")
    suspend fun getBlogDetail(
        @Query("id") mainId: Int = RemoteModelConst.MAIN_ID,
        @Query("blog_id") blogId: Int
    ): BlogDetailResponse
}