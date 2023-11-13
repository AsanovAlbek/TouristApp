package albek.petprojects.touristapp.feature.home.data.model

data class BlogPreviewDto(
    val id: Int = 0,
    val title: String = "",
    val subTitle: String = "",
    val image: BlogImageModelDto = BlogImageModelDto()
)
