package albek.petprojects.touristapp.feature.detail.presentation.orbit

sealed class DetailAction {
    data class GetDetailById(val blogId: Int): DetailAction()
    object NavigateBack: DetailAction()
}
