package albek.petprojects.touristapp.feature.home.data.model

data class HomeContentDto(
    val buttons: List<HomeButtonModelDto> = emptyList(),
    val content: List<HomeSectionModelDto> = emptyList()
)
