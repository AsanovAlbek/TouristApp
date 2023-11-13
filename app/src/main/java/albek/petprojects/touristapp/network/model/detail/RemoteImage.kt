package albek.petprojects.touristapp.network.model.detail

import com.google.gson.annotations.SerializedName

data class RemoteImage(
    @SerializedName("sm")
    val smallImageUrl: String = "",
    @SerializedName("md")
    val mediumImageUrl: String = "",
    @SerializedName("lg")
    val bigImageUrl: String = "",
)
