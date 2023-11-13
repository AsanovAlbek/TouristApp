package albek.petprojects.touristapp.feature.home.presentation.orbit

sealed class HomeAction {
    data class OpenDetails(val blogId: Int = 0): HomeAction()
    object ShowAllBlogPreviews: HomeAction()
    object LoadOrRefresh: HomeAction()
}

