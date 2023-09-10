package edu.uchicago.gerber.androidretro.presentation.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.androidretro.common.DrinksConstants
import edu.uchicago.gerber.androidretro.presentation.viewmodels.DrinksViewModel
import edu.uchicago.gerber.androidretro.presentation.widgets.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(drinksViewModel: DrinksViewModel,
                    navController: NavController) {
    val state = drinksViewModel.favoritesState.value

    Scaffold(
        modifier = DrinksConstants.modifier,
        bottomBar = { BottomNavigationBar(navController) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Favorites",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Refresh",
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable {
                                drinksViewModel.onFavoritesList()
                            },
                        style = TextStyle(color = Color.Black),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = Color(0x37000000))
                .wrapContentSize(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            when (state.favoritesOperation) {
                DrinksFavoritesOperation.INITIAL -> {
                    drinksViewModel.onFavoritesList()
                }
                DrinksFavoritesOperation.LOADING -> {
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
                DrinksFavoritesOperation.DONE -> {
                    DrinksFavoritesList(drinksViewModel, navController)
                }
            }
        }
    }
}