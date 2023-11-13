package albek.petprojects.touristapp.feature.detail.presentation

import albek.petprojects.touristapp.R
import albek.petprojects.touristapp.core.ContentState
import albek.petprojects.touristapp.core.composable.ErrorPanel
import albek.petprojects.touristapp.core.composable.picassoPainter
import albek.petprojects.touristapp.core.util.formatDateTime
import albek.petprojects.touristapp.feature.detail.domain.model.Detail
import albek.petprojects.touristapp.feature.detail.presentation.orbit.DetailAction
import albek.petprojects.touristapp.feature.detail.presentation.orbit.DetailsEffect
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectSideEffect
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    blogId: Int,
    detailViewModel: DetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = detailViewModel.container.stateFlow.collectAsState()

    detailViewModel.collectSideEffect { effect ->
        when (effect) {
            DetailsEffect.NavigateBack -> navController.popBackStack()
        }
    }

    detailViewModel.initViewModel()
    detailViewModel.doAction(DetailAction.GetDetailById(blogId))

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state.value.contentState) {
            ContentState.CONTENT, ContentState.EMPTY -> DetailContentScreen(
                detail = state.value.detail,
                navigateBack = { detailViewModel.doAction(DetailAction.NavigateBack) })

            ContentState.ERROR -> ErrorPanel(errorModel = state.value.errorModel)
            ContentState.LOADING -> Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }
        }
    }
}

@Composable
fun DetailContentScreen(
    detail: Detail,
    navigateBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        ) {
        Image(
            painter = picassoPainter(
                context = LocalContext.current,
                imageUrl = detail.image.mediumImageUrl
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = detail.date.formatDateTime(), Modifier.padding(horizontal = 16.dp))
        Text(
            text = detail.title,
            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = detail.content,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(16.dp)
        )
    }

    FloatingActionButton(
        onClick = navigateBack,
        modifier = Modifier.offset(16.dp, 24.dp),
        containerColor = Color.White,
        shape = CircleShape,
    ) {
        Image(
            painter = painterResource(id = R.drawable.navigate_back_icon),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailContentScreenPreview() {
    DetailContentScreen(detail = Detail(title = "title", content = "content"))
}