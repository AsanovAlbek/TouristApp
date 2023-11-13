package albek.petprojects.touristapp.feature.detail.presentation.orbit

import albek.petprojects.touristapp.core.ContentState
import albek.petprojects.touristapp.core.ErrorModel
import albek.petprojects.touristapp.feature.detail.domain.model.Detail

data class DetailState(
    val detail: Detail = Detail(),
    val errorModel: ErrorModel = ErrorModel(),
    val contentState: ContentState = ContentState.LOADING
)