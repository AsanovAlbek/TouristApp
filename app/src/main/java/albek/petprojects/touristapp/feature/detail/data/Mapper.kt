package albek.petprojects.touristapp.feature.detail.data

import albek.petprojects.touristapp.feature.detail.data.model.DetailDto
import albek.petprojects.touristapp.feature.detail.data.model.DetailImageDto
import albek.petprojects.touristapp.feature.detail.domain.model.Detail
import albek.petprojects.touristapp.feature.detail.domain.model.DetailImage
import albek.petprojects.touristapp.network.model.detail.RemoteBlogContent
import albek.petprojects.touristapp.network.model.detail.RemoteImage

fun RemoteBlogContent.toData() = DetailDto(
    id = id,
    title = title,
    content = content,
    subTitle = subTitle,
    date = date,
    image = image.toData()
)

fun RemoteImage.toData() = DetailImageDto(
    smallImageUrl, mediumImageUrl, bigImageUrl
)

fun DetailDto.toDomain() = Detail(
    id = id,
    title = title,
    content = content,
    subTitle = subTitle,
    date = date,
    image = image.toDomain()
)

fun DetailImageDto.toDomain() = DetailImage(
    smallImageUrl, mediumImageUrl, bigImageUrl
)