package edu.uchicago.gerber.androidretro.presentation.screens.favorites

import edu.uchicago.gerber.androidretro.presentation.viewmodels.DrinksViewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import edu.uchicago.gerber.androidretro.presentation.navigation.Screen
import edu.uchicago.gerber.androidretro.presentation.screens.search.drinks.DrinksRow

@Composable
fun DrinksFavoritesList(drinksViewModel: DrinksViewModel, navController: NavController) {

    //this is what consumes the flow
    val lazyPagingItems = drinksViewModel.favoritesState.value.data?.collectAsLazyPagingItems()

    LazyColumn {
        items(
            count = lazyPagingItems!!.itemCount,
            key = lazyPagingItems.itemKey(),
            contentType = lazyPagingItems.itemContentType()
        ) { index ->
            //the following lines define the onItemClick behavior
            val boolItem = lazyPagingItems[index]!!
            DrinksRow(drinks = boolItem) {
                //the following lines define the onItemClick behavior
                drinksViewModel.setDrinksFavorites(boolItem)
                navController.navigate(
                    route = Screen.DrinksDetail.route
                )
            }

        }

        //this will display a spinner in-place of a drinksRow in the following events
        lazyPagingItems.apply {
            //fallthrough is not supported
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
            }
        }
    }
}
@Composable
fun Spinner() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(12.dp)
                .align(
                    Alignment.Center
                )
        )
    }
}