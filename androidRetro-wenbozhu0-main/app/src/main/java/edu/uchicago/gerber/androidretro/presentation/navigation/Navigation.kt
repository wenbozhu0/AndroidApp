package edu.uchicago.gerber.androidretro.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import edu.uchicago.gerber.androidretro.presentation.screens.contact.ContactScreen
import edu.uchicago.gerber.androidretro.presentation.screens.details.DrinksDetailScreen
import edu.uchicago.gerber.androidretro.presentation.screens.favorites.FavoritesScreen
import edu.uchicago.gerber.androidretro.presentation.screens.search.drinks.DrinksSearchScreen
import edu.uchicago.gerber.androidretro.presentation.viewmodels.ContactViewModel
import edu.uchicago.gerber.androidretro.presentation.viewmodels.DrinksViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    drinksViewModel: DrinksViewModel = viewModel(),
    contactViewModel: ContactViewModel = viewModel()
) {

    AnimatedNavHost(navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            DrinksSearchScreen(drinksViewModel , navController)
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(drinksViewModel, navController)
        }

        composable(Screen.Contact.route) {
            ContactScreen(contactViewModel, navController)
        }

        composable(Screen.DrinksDetail.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(300))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
            },) {
            DrinksDetailScreen(drinksViewModel, navController )
        }
    }
}