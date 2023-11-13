package albek.petprojects.touristapp.feature.home.domain.usecase

import albek.petprojects.touristapp.feature.home.data.mapper.toDomain
import albek.petprojects.touristapp.feature.home.domain.repository.HomeRepository
import javax.inject.Inject

class ShowPreviewsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    companion object {
        // При первой загрузке нужно показать не все превью, а только несколько
        const val TAKE_FIRST_PREVIEWS_COUNT = 5
    }

    suspend operator fun invoke(showAll: Boolean = false) =
        repository
            .blogPreviews()
            .map { it.toDomain() }
            .let { previews ->
                if (showAll) {
                    previews
                } else {
                    previews.take(TAKE_FIRST_PREVIEWS_COUNT)
                }
            }
}