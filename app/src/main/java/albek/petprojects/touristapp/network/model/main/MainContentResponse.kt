package albek.petprojects.touristapp.network.model.main

import com.google.gson.annotations.SerializedName

data class MainContentResponse(
    @SerializedName("success")
    val isSuccess: Boolean = false,
    @SerializedName("data")
    val contentDataList: RemoteContentData = RemoteContentData()
)