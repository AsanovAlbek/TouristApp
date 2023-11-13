package albek.petprojects.touristapp.application

import albek.petprojects.touristapp.application.theme.TouristAppTheme
import albek.petprojects.touristapp.feature.detail.presentation.DetailScreen
import albek.petprojects.touristapp.feature.detail.presentation.DetailViewModel
import albek.petprojects.touristapp.feature.home.presentation.HomeScreen
import albek.petprojects.touristapp.feature.home.presentation.HomeViewModel
import albek.petprojects.touristapp.navigation.NavDirection
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TouristAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavDirection.HOME.route
                    ) {
                        composable(NavDirection.HOME.route) {
                            HomeScreen(
                                homeViewModel = hiltViewModel(),
                                navController = navController
                            )
                        }
                        composable(
                            route = NavDirection.DETAIL.route,
                            arguments = listOf(navArgument("blogId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val blogId = backStackEntry.arguments?.getInt("blogId") ?: 0
                            DetailScreen(
                                blogId = blogId,
                                detailViewModel = hiltViewModel(),
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}