package albek.petprojects.touristapp.feature.home.presentation

import albek.petprojects.touristapp.core.BaseViewModel
import albek.petprojects.touristapp.core.ContentState
import albek.petprojects.touristapp.feature.home.domain.usecase.GetHomeContentUseCase
import albek.petprojects.touristapp.feature.home.domain.usecase.ShowPreviewsUseCase
import albek.petprojects.touristapp.feature.home.presentation.orbit.HomeAction
import albek.petprojects.touristapp.feature.home.presentation.orbit.HomeEffect
import albek.petprojects.touristapp.feature.home.presentation.orbit.HomeState
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeContentUseCase: GetHomeContentUseCase,
    private val showPreviewsUseCase: ShowPreviewsUseCase
) : BaseViewModel<HomeState, HomeEffect, HomeAction>() {
    override val container = container<HomeState, HomeEffect>(HomeState())

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

    override fun doAction(action: HomeAction) {
        when (action) {
            is HomeAction.OpenDetails -> openDetails(action.blogId)
            HomeAction.LoadOrRefresh -> loadOrRefresh()
            HomeAction.ShowAllBlogPreviews -> showAllBlogs()
        }
    }

    private fun showAllBlogs() = runOnDefault {
        intent {
            reduce { state.copy(showAllPreviews = true) }
        }
    }

    private fun loadOrRefresh() = runBlockingOnIo {
        Log.d("state", "load")
        intent {
            reduce {
                state.copy(contentState = ContentState.LOADING)
            }
        }
        getHomeContentUseCase(
            showPreviewsUseCase(
                showAll = container.stateFlow.value.showAllPreviews
            )
        ).let { content ->
            val contentState =
                if (content.content.isEmpty()) ContentState.EMPTY else ContentState.CONTENT
            val hidePreviewsCount =
                if (content.content.size - 5 > 0) content.content.size - 5 else 0

            intent {
                reduce {
                    state.copy(
                        homeContent = content,
                        contentState = contentState,
                        hidePreviewsCount = hidePreviewsCount
                    )
                }
            }
        }
    }

    private fun openDetails(blogId: Int) = runOnDefault {
        intent { postSideEffect(HomeEffect.OpenBlogDetail(blogId)) }
    }
}