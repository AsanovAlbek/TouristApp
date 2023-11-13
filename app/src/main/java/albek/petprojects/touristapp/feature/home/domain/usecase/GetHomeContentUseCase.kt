package albek.petprojects.touristapp.feature.home.domain.usecase

import albek.petprojects.touristapp.feature.home.data.mapper.toData
import albek.petprojects.touristapp.feature.home.data.mapper.toDomain
import albek.petprojects.touristapp.feature.home.domain.model.BlogPreview
import albek.petprojects.touristapp.feature.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeContentUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(blogPreviews: List<BlogPreview>) =
        repository.mainPage().toDomain(blogPreviews.map { it.toData() })
}