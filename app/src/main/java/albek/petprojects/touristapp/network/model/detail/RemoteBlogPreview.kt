package albek.petprojects.touristapp.network.model.detail

import com.google.gson.annotations.SerializedName

data class RemoteBlogPreview(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("subtitle")
    val subTitle: String = "",
    @SerializedName("image")
    val image: RemoteImage = RemoteImage()
)
