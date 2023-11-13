package albek.petprojects.touristapp.feature.home.presentation.orbit

sealed interface HomeEffect {
    data class OpenBlogDetail(val blogId: Int): HomeEffect
}