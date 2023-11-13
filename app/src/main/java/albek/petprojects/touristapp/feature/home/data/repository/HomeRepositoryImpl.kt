package albek.petprojects.touristapp.feature.home.data.repository

import albek.petprojects.touristapp.core.UnsuccessfulException
import albek.petprojects.touristapp.feature.home.data.mapper.toData
import albek.petprojects.touristapp.feature.home.data.model.BlogPreviewDto
import albek.petprojects.touristapp.feature.home.data.model.HomeContentDto
import albek.petprojects.touristapp.feature.home.domain.repository.HomeRepository
import albek.petprojects.touristapp.network.retrofit.TouristAppApiService

class HomeRepositoryImpl(
    private val apiService: TouristAppApiService
): HomeRepository {
    override suspend fun mainPage(): HomeContentDto =
        apiService.getMainPage().let { mainContentResponse ->
            if (!mainContentResponse.isSuccess) throw UnsuccessfulException()
            mainContentResponse.contentDataList.toData(blogPreviews())
        }

    override suspend fun blogPreviews(): List<BlogPreviewDto> =
        apiService.getBlogPreviews().let { blogPreviewResponse ->
            if (!blogPreviewResponse.isSuccess) throw UnsuccessfulException()
            blogPreviewResponse.blogPreviews.map { preview -> preview.toData() }
        }
}