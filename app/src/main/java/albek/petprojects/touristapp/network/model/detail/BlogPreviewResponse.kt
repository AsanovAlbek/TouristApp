package albek.petprojects.touristapp.network.model.detail

import com.google.gson.annotations.SerializedName

data class BlogPreviewResponse(
    @SerializedName("success")
    val isSuccess: Boolean = false,
    @SerializedName("data")
    val blogPreviews: List<RemoteBlogPreview> = emptyList()
)
