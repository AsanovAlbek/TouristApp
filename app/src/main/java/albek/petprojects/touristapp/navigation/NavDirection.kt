package albek.petprojects.touristapp.navigation

enum class NavDirection(
    val route: String
) {
    HOME("home"), DETAIL("detail/{blogId}")
}