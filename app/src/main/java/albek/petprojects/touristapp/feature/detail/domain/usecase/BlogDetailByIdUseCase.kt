package albek.petprojects.touristapp.feature.detail.domain.usecase

import albek.petprojects.touristapp.feature.detail.data.toDomain
import albek.petprojects.touristapp.feature.detail.domain.repository.DetailRepository
import javax.inject.Inject

class BlogDetailByIdUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(blogId: Int) =
        repository.blogDetailById(blogId = blogId).toDomain()
}