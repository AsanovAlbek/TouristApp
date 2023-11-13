package albek.petprojects.touristapp.feature.home.domain.model

data class HomeContent(
    val buttons: List<HomeButtonModel> = emptyList(),
    val content: List<HomeSectionModel> = emptyList()
)
