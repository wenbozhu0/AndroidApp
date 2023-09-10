package edu.uchicago.gerber.androidretro.presentation.screens.search.drinks

import edu.uchicago.gerber.androidretro.presentation.viewmodels.DrinksViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.androidretro.common.DrinksConstants
import edu.uchicago.gerber.androidretro.presentation.widgets.BottomNavigationBar
import edu.uchicago.gerber.androidretro.presentation.widgets.CustomOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksSearchScreen(
    drinksViewModel: DrinksViewModel,
    navController: NavController,
) {
    val state = drinksViewModel.searchState.value
    val queryText = drinksViewModel.queryText.value

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
                        text = "Search Drinks",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )

                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CustomOutlinedTextField(
                title = "Search drinks(s)",
                placeHolder = "e.g. vodka",
                textState = queryText,
                onTextChange = drinksViewModel::setQueryText,
                keyboardType = KeyboardType.Text,
                ImeAction.Search,
                drinksViewModel::onSearch
            )

            Spacer(modifier = Modifier.height(10.dp))
            when (state.searchOperation) {
                DrinksSearchOperation.LOADING -> {
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
                DrinksSearchOperation.DONE -> {
                    DrinksList(drinksViewModel, navController)
                }
                else -> {
                    Box {}
                }
            }
        }
    }
}