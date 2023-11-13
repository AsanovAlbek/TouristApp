package albek.petprojects.touristapp.network.model.main

import com.google.gson.annotations.SerializedName

data class RemoteContentData(
    @SerializedName("buttons")
    val buttons: List<RemoteButton> = emptyList(),
    @SerializedName("content")
    val contentDataList: List<RemoteContent> = emptyList()
)