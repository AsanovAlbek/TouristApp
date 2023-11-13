package albek.petprojects.touristapp.feature.home.presentation.orbit

import albek.petprojects.touristapp.core.ContentState
import albek.petprojects.touristapp.core.ErrorModel
import albek.petprojects.touristapp.feature.home.domain.model.HomeContent

data class HomeState(
    val homeContent: HomeContent = HomeContent(),
    val errorModel: ErrorModel = ErrorModel(),
    val showAllPreviews: Boolean = false,
    val hidePreviewsCount: Int = 0,
    val contentState: ContentState = ContentState.LOADING
)