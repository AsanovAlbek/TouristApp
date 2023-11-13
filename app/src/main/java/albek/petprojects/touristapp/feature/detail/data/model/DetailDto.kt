package albek.petprojects.touristapp.feature.detail.data.model


data class DetailDto(
    val id: Int = 0,
    val image: DetailImageDto = DetailImageDto(),
    val title: String = "",
    val subTitle: String = "",
    val date: String = "",
    val content: String = ""
)
