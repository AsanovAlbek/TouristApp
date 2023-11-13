package albek.petprojects.touristapp.feature.home.domain.model

data class HomeSectionModel(
    val title: String = "",
    val template: Template = Template(),
    val previews: List<BlogPreview> = emptyList()
)