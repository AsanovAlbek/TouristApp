package albek.petprojects.touristapp.feature.home.data.mapper

import albek.petprojects.touristapp.feature.home.data.model.BlogImageModelDto
import albek.petprojects.touristapp.feature.home.data.model.BlogPreviewDto
import albek.petprojects.touristapp.feature.home.data.model.HomeButtonModelDto
import albek.petprojects.touristapp.feature.home.data.model.HomeContentDto
import albek.petprojects.touristapp.feature.home.data.model.HomeSectionModelDto
import albek.petprojects.touristapp.feature.home.data.model.TemplateDto
import albek.petprojects.touristapp.feature.home.domain.model.BlogImageModel
import albek.petprojects.touristapp.feature.home.domain.model.BlogPreview
import albek.petprojects.touristapp.feature.home.domain.model.HomeButtonModel
import albek.petprojects.touristapp.feature.home.domain.model.HomeContent
import albek.petprojects.touristapp.feature.home.domain.model.HomeSectionModel
import albek.petprojects.touristapp.feature.home.domain.model.Template
import albek.petprojects.touristapp.network.model.detail.RemoteBlogPreview
import albek.petprojects.touristapp.network.model.detail.RemoteImage
import albek.petprojects.touristapp.network.model.main.RemoteButton
import albek.petprojects.touristapp.network.model.main.RemoteContent
import albek.petprojects.touristapp.network.model.main.RemoteContentData
import albek.petprojects.touristapp.network.model.main.RemoteTemplate

// network to data
fun RemoteImage.toData() = BlogImageModelDto(
    smallImageUrl = smallImageUrl,
    mediumImageUrl = mediumImageUrl,
    bigImageUrl = bigImageUrl
)

fun RemoteButton.toData() =
    HomeButtonModelDto(icon = icon, color = color, title = title, type = type, url = url)

fun RemoteTemplate.toData() =
    TemplateDto(card = card, size = size, objectType = objectType, direction = direction)

fun RemoteBlogPreview.toData() = BlogPreviewDto(
    id = id, title = title, subTitle = subTitle, image = image.toData()
)

fun RemoteContent.toData(blogPreviews: List<BlogPreviewDto>) =
    HomeSectionModelDto(title = title, template = template.toData(), previews = blogPreviews)

fun RemoteContentData.toData(blogPreviews: List<BlogPreviewDto>) =
    HomeContentDto(
        buttons = buttons.map { it.toData() },
        content = contentDataList.map { it.toData(blogPreviews = blogPreviews) }
    )

// data to domain
fun HomeSectionModelDto.toDomain(blogPreviews: List<BlogPreviewDto>) =
    HomeSectionModel(title = title, template = template.toDomain(), previews = blogPreviews.map { it.toDomain() })

fun HomeContentDto.toDomain(blogPreviews: List<BlogPreviewDto>) =
    HomeContent(
        buttons = buttons.map { it.toDomain() },
        content = content.map { it.toDomain(blogPreviews = blogPreviews) }
    )

fun BlogImageModelDto.toDomain() = BlogImageModel(
    smallImageUrl = smallImageUrl,
    mediumImageUrl = mediumImageUrl,
    bigImageUrl = bigImageUrl
)

fun HomeButtonModelDto.toDomain() =
    HomeButtonModel(icon = icon, color = color, title = title, type = type, url = url)

fun TemplateDto.toDomain() =
    Template(card = card, size = size, objectType = objectType, direction = direction)

fun BlogPreviewDto.toDomain() = BlogPreview(
    id = id, title = title, subTitle = subTitle, image = image.toDomain()
)

// domain to data

fun HomeSectionModel.toData(blogPreviews: List<BlogPreviewDto>) =
    HomeSectionModelDto(title = title, template = template.toData(), previews = blogPreviews)

fun HomeContent.toData(blogPreviews: List<BlogPreviewDto>) =
    HomeContentDto(
        buttons = buttons.map { it.toData() },
        content = content.map { it.toData(blogPreviews = blogPreviews) }
    )

fun BlogImageModel.toData() = BlogImageModelDto(
    smallImageUrl = smallImageUrl,
    mediumImageUrl = mediumImageUrl,
    bigImageUrl = bigImageUrl
)

fun HomeButtonModel.toData() =
    HomeButtonModelDto(icon = icon, color = color, title = title, type = type, url = url)

fun Template.toData() =
    TemplateDto(card = card, size = size, objectType = objectType, direction = direction)

fun BlogPreview.toData() = BlogPreviewDto(
    id = id, title = title, subTitle = subTitle, image = image.toData()
)




