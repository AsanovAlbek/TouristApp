package albek.petprojects.touristapp.feature.home.domain.repository

import albek.petprojects.touristapp.feature.home.data.model.BlogPreviewDto
import albek.petprojects.touristapp.feature.home.data.model.HomeContentDto

interface HomeRepository {
    suspend fun mainPage(): HomeContentDto
    suspend fun blogPreviews(): List<BlogPreviewDto>
}