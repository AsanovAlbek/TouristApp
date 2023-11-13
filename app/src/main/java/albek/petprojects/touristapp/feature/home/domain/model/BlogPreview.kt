package albek.petprojects.touristapp.feature.home.domain.model

data class BlogPreview(
    val id: Int = 0,
    val title: String = "",
    val subTitle: String = "",
    val image: BlogImageModel = BlogImageModel()
)
