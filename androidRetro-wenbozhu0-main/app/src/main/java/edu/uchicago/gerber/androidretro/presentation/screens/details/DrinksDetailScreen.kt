package edu.uchicago.gerber.androidretro.presentation.screens.details

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import edu.uchicago.gerber.androidretro.R
import edu.uchicago.gerber.androidretro.common.DrinksConstants
import edu.uchicago.gerber.androidretro.presentation.viewmodels.DrinksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksDetailScreen(
    drinksViewModel: DrinksViewModel,
    navController: NavController
) {

    //observe the book
    val drinks = drinksViewModel.drinks.value
    val activity = (LocalContext.current as? Activity)


    Scaffold(
        modifier = DrinksConstants.modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                ) ,
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier
                                .clickable {
                                    navController.popBackStack()
                                }
                                .align(Alignment.CenterVertically)
                                .padding(20.dp, 0.dp, 0.dp, 0.dp))

                        Text(
                            text = "Details",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp
                        )

                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {

                            Icon(imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                modifier = Modifier
                                    .clickable {
                                        val sendIntent = Intent(Intent.ACTION_SEND)
                                        sendIntent.type = "text/plain"
                                        sendIntent.putExtra(
                                            Intent.EXTRA_TEXT,
                                            "You must check out this drinks!: ${drinks.strDrink}"
                                        )
                                        activity?.startActivity(sendIntent)
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp))
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(painter = painterResource(id = R.drawable.ic_navigation),
                                contentDescription = "Map",

                                modifier = Modifier
                                    .clickable {
                                        //this would be used for navigating if you have a physical address
                                        val oberweisDairyAddress =
                                            "9 E Dundee Rd, Arlington Heights, IL 60004"
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(
                                                "google.navigation:q=$oberweisDairyAddress"
                                            )
                                        )
                                        activity?.startActivity(intent)
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(10.dp, 0.dp, 10.dp, 0.dp))
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(imageVector = Icons.Default.Phone,
                                contentDescription = "Phone",

                                modifier = Modifier
                                    .clickable {
                                        //this would be used for dialing if you have a phone number
                                        val phoneNumber = "800-555-1212"
                                        val intent = Intent(
                                            Intent.ACTION_DIAL,
                                            Uri.parse("tel:$phoneNumber")
                                        )
                                        activity?.startActivity(intent)
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(0.dp, 0.dp, 20.dp, 0.dp))
                        }

                    }

                })
        }) {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues = paddingValues)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState(0))
                .padding(20.dp, 0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {


                Divider()
                Spacer(Modifier.height(20.dp))
                //we can use either coil or glide for images. If you need animations, use coil
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    imageModel = drinks.strDrinkThumb,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Fit

                )
                with(drinks){
                    idDrink?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Start,
                            fontSize = 22.sp
                        )
                    }
                    strDrink?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp
                        )
                    }
                    strGlass?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp
                        )
                    }
                    strInstructions?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp
                        )
                    }
                }

                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        drinksViewModel.onAddToFav(drinks)
                        Toast.makeText(activity, "Add to Favorites", Toast.LENGTH_LONG).show()
                    },

                    colors =
                    ButtonDefaults.buttonColors(containerColor = Color.Green)

                ) {
                    Text(text = "Add to Favorites")
                }

            }
        }
    }

}