package albek.petprojects.touristapp.feature.home.presentation.compose

import albek.petprojects.touristapp.core.composable.picassoPainter
import albek.petprojects.touristapp.feature.home.domain.model.HomeButtonModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeButton(
    buttonModel: HomeButtonModel,
    click: () -> Unit = {}
) {
    Button(
        onClick = click,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(CornerSize(8.dp)),
        modifier = Modifier.wrapContentSize()
    ) {
        Image(
            painter = picassoPainter(
                context = LocalContext.current,
                imageUrl = buttonModel.url
            ),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = buttonModel.title)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeButtonPreview() {
    HomeButton(HomeButtonModel(title = "Button"))
}