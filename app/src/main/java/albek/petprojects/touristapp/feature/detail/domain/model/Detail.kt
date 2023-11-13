package albek.petprojects.touristapp.feature.detail.domain.model


data class Detail(
    val id: Int = 0,
    val image: DetailImage = DetailImage(),
    val title: String = "",
    val subTitle: String = "",
    val date: String = "",
    val content: String = ""
)
