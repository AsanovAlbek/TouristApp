package albek.petprojects.touristapp.feature.home.presentation

import albek.petprojects.touristapp.R
import albek.petprojects.touristapp.core.ContentState
import albek.petprojects.touristapp.core.composable.ErrorPanel
import albek.petprojects.touristapp.feature.home.presentation.compose.HomeButton
import albek.petprojects.touristapp.feature.home.presentation.compose.PreviewCard
import albek.petprojects.touristapp.feature.home.presentation.orbit.HomeAction
import albek.petprojects.touristapp.feature.home.presentation.orbit.HomeEffect
import albek.petprojects.touristapp.feature.home.presentation.orbit.HomeState
import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = homeViewModel.container.stateFlow.collectAsState()
    val resource = LocalContext.current.resources
    homeViewModel.collectSideEffect { effect ->
        when (effect) {
            is HomeEffect.OpenBlogDetail -> navController.navigate("detail/${effect.blogId}")
        }
    }

    homeViewModel.initViewModel()
    homeViewModel.doAction(HomeAction.LoadOrRefresh)
    Log.d("state", state.value.contentState.toString())

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    stringResource(R.string.main_top_app_bar_title),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                )
            }
        )
        when (state.value.contentState) {
            ContentState.CONTENT, ContentState.EMPTY -> HomeContentScreen(
                state = state,
                openDetails = { id -> homeViewModel.doAction(HomeAction.OpenDetails(id)) },
                showAll = { homeViewModel.doAction(HomeAction.ShowAllBlogPreviews) }
            )

            ContentState.ERROR -> ErrorPanel(errorModel = state.value.errorModel)
            ContentState.LOADING -> LoadingScreen()
        }
    }
}

@Composable
fun HomeContentScreen(
    state: State<HomeState>,
    openDetails: (blogId: Int) -> Unit = {},
    showAll: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        state.value.homeContent.buttons.forEach { button ->
            HomeButton(buttonModel = button)
        }
    }
    if (state.value.contentState == ContentState.CONTENT) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            state.value.homeContent.content.forEach { section ->
                item {
                    Text(
                        text = section.title,
                        style = TextStyle(fontSize = 24.sp),
                        modifier = Modifier
                            .offset(16.dp)
                            .padding(vertical = 16.dp)
                    )
                }
                items(section.previews) { preview ->
                    PreviewCard(preview = preview, cardClick = openDetails)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (!state.value.showAllPreviews) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = showAll,
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(size = 8.dp),
                        ) {
                            Text(
                                text = stringResource(id = R.string.show_all_button_text),
                                style = TextStyle(fontSize = 18.sp)
                            )
                        }
                    }
                }
            }
        }
    } else {
        Text(text = stringResource(R.string.empty_text))
    }
}

@Composable
fun LoadingScreen() {
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp)
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}