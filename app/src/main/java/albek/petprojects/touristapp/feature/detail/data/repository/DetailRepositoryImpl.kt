package albek.petprojects.touristapp.feature.detail.data.repository

import albek.petprojects.touristapp.core.UnsuccessfulException
import albek.petprojects.touristapp.feature.detail.data.model.DetailDto
import albek.petprojects.touristapp.feature.detail.data.toData
import albek.petprojects.touristapp.feature.detail.domain.repository.DetailRepository
import albek.petprojects.touristapp.network.retrofit.TouristAppApiService

class DetailRepositoryImpl(
    private val apiService: TouristAppApiService
): DetailRepository {
    override suspend fun blogDetailById(blogId: Int): DetailDto =
        apiService.getBlogDetail(blogId = blogId).let { response ->
            if (!response.isSuccess) throw UnsuccessfulException()
            response.blogDetail.toData()
        }
}