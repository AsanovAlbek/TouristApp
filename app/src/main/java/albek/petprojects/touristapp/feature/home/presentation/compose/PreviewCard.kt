package albek.petprojects.touristapp.feature.home.presentation.compose

import albek.petprojects.touristapp.core.composable.picassoPainter
import albek.petprojects.touristapp.feature.home.domain.model.BlogPreview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreviewCard(
    preview: BlogPreview,
    cardClick: (blogId: Int) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { cardClick(preview.id) }
    ) {
        Image(
            painter = picassoPainter(
                context = LocalContext.current,
                imageUrl = preview.image.smallImageUrl
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(90.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = preview.title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Text(text = preview.subTitle, style = TextStyle(fontSize = 16.sp), maxLines = 3)
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCardPreview() {
    PreviewCard(preview = BlogPreview(title = "title", subTitle = "subtitle\nsubtitle\nsubtitle"))
}