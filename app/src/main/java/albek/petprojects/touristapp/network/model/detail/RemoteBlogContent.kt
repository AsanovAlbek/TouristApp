package albek.petprojects.touristapp.network.model.detail

import com.google.gson.annotations.SerializedName

data class RemoteBlogContent(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: RemoteImage = RemoteImage(),
    @SerializedName("title")
    val title: String = "",
    @SerializedName("date")
    val date: String = "",
    @SerializedName("subtitle")
    val subTitle: String = "",
    @SerializedName("content")
    val content: String = ""
)
