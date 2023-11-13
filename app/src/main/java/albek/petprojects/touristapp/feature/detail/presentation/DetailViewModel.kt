package albek.petprojects.touristapp.feature.detail.presentation

import albek.petprojects.touristapp.core.BaseViewModel
import albek.petprojects.touristapp.core.ContentState
import albek.petprojects.touristapp.feature.detail.domain.usecase.BlogDetailByIdUseCase
import albek.petprojects.touristapp.feature.detail.presentation.orbit.DetailAction
import albek.petprojects.touristapp.feature.detail.presentation.orbit.DetailState
import albek.petprojects.touristapp.feature.detail.presentation.orbit.DetailsEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val blogDetailByIdUseCase: BlogDetailByIdUseCase
) : BaseViewModel<DetailState, DetailsEffect, DetailAction>() {
    override val container = container<DetailState, DetailsEffect>(DetailState())

    override fun doAction(action: DetailAction) {
        when (action) {
            DetailAction.NavigateBack -> navigateBack()
            is DetailAction.GetDetailById -> blogById(action.blogId)
        }
    }

    fun initViewModel() = runOnUi {
        errorFlow.collect { errorModel ->
            intent {
                reduce {
                    state.copy(
                        contentState = ContentState.ERROR,
                        errorModel = errorModel
                    )
                }
            }
        }
    }

    private fun blogById(blogId: Int) = runOnIo {
        intent { reduce { state.copy(contentState = ContentState.LOADING) } }
        blogDetailByIdUseCase(blogId = blogId).let { detail ->
            intent { reduce { state.copy(detail = detail, contentState = ContentState.CONTENT) } }
        }
    }

    private fun navigateBack() = runOnDefault {
        intent { postSideEffect(DetailsEffect.NavigateBack) }
    }
}