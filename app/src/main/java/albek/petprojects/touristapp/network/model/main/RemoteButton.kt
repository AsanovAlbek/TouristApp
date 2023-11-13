package albek.petprojects.touristapp.network.model.main

import com.google.gson.annotations.SerializedName

data class RemoteButton(
    @SerializedName("icon")
    val icon: String = "",
    @SerializedName("color")
    val color: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
)
