package albek.petprojects.touristapp.feature.home.data.model

data class HomeSectionModelDto(
    val title: String = "",
    val template: TemplateDto = TemplateDto(),
    val previews: List<BlogPreviewDto> = emptyList()
)
