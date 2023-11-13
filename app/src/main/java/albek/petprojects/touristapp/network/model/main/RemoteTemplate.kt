package albek.petprojects.touristapp.network.model.main

import com.google.gson.annotations.SerializedName

data class RemoteTemplate(
    @SerializedName("card")
    val card: String = "",
    @SerializedName("size")
    val size: String = "",
    @SerializedName("direction")
    val direction: String = "",
    @SerializedName("object")
    val objectType: String = ""
)