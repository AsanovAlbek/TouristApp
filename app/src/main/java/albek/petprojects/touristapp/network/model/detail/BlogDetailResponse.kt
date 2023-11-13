package albek.petprojects.touristapp.network.model.detail

import com.google.gson.annotations.SerializedName

data class BlogDetailResponse(
    @SerializedName("success")
    val isSuccess: Boolean = false,
    @SerializedName("data")
    val blogDetail: RemoteBlogContent = RemoteBlogContent()
)
