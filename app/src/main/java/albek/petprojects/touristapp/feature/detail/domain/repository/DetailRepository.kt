package albek.petprojects.touristapp.feature.detail.domain.repository

import albek.petprojects.touristapp.feature.detail.data.model.DetailDto

interface DetailRepository {
    suspend fun blogDetailById(blogId: Int): DetailDto
}