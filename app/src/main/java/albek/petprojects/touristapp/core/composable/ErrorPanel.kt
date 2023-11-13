package albek.petprojects.touristapp.core.composable

import albek.petprojects.touristapp.R
import albek.petprojects.touristapp.core.ErrorModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorPanel(errorModel: ErrorModel) {
    val resources = LocalContext.current.resources
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(errorModel.image),
            contentDescription = resources.getString(R.string.error_image_content_description),
            modifier = Modifier.size(170.dp)
        )
        Text(
            text = resources.getString(errorModel.messageId),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun ErrorPanelPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ErrorPanel(
                errorModel = ErrorModel(
                    image = R.drawable.error_icon,
                    messageId = R.string.error_image_content_description
                )
            )
        }
    }
}