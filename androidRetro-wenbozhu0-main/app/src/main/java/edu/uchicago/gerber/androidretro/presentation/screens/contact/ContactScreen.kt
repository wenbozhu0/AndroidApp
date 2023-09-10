package edu.uchicago.gerber.androidretro.presentation.screens.contact

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.androidretro.common.DrinksConstants
import edu.uchicago.gerber.androidretro.presentation.viewmodels.ContactViewModel
import edu.uchicago.gerber.androidretro.presentation.widgets.BottomNavigationBar
import edu.uchicago.gerber.androidretro.presentation.widgets.CustomOutlinedTextField

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(contactViewModel: ContactViewModel,
                  navController: NavController) {
    var subject = remember{ mutableStateOf(TextFieldValue("subject")) }
    val message = mutableStateOf(TextFieldValue("message"))
    val activity = (LocalContext.current as? Activity)

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
                        text = "Contact Us",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )

                }
                )
        }
    ) {
            paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = Color(0xFFFFFFF))
        ) {
            Text(
                text = "Cocktail Shop",
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            Text (
                text = "50 sample street, NewYork, NY11012",
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 30.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Text (
                text = "Tel: 123-456-7890",
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 30.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            TextField(
                value = subject.value,
                onValueChange = {subject.value = it},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 30.dp),
                textStyle = TextStyle(color = Color.Black)
            )
            TextField(
                value = message.value,
                onValueChange = {message.value = it},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 30.dp),
                textStyle = TextStyle(color = Color.Black)
            )

            Button(
                modifier =
                Modifier
                    .padding(10.dp, 0.dp)
                    .fillMaxWidth(1f),

                onClick = {
                    contactViewModel.sendMessage(subject.value.text, message.value.text)
                    Toast.makeText(activity, "Send Message", Toast.LENGTH_LONG).show()
                },

                colors =
                ButtonDefaults.buttonColors(containerColor = Color.Green)

            ) {
                Text(text = "SEND MESSAGE")
            }
        }
    }
}