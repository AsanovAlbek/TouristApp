package albek.petprojects.touristapp.network.model.main

import com.google.gson.annotations.SerializedName

data class RemoteContent(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("template")
    val template: RemoteTemplate = RemoteTemplate(),
    @SerializedName("url")
    val url: String = ""
)
