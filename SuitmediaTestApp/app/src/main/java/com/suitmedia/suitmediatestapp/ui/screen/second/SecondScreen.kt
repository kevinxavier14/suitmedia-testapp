package com.suitmedia.suitmediatestapp.ui.screen.second

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.suitmedia.suitmediatestapp.ui.navigation.Screen
import com.suitmedia.suitmediatestapp.ui.session.UserSessionViewModel

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userSessionViewModel: UserSessionViewModel
) {
    val userName: String by userSessionViewModel.userName.observeAsState("")
    val apiName: String by userSessionViewModel.apiName.observeAsState("")

    val textValue = if (apiName != "") apiName else "Selected Username"

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "Back",
                    tint = Color(0xFF554AF0),
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = "Second Screen",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "",
                    tint = Color.Transparent, // Make the icon transparent
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // welcome text
            Text(
                text = "Welcome",
                style = TextStyle(
                    fontSize = 12.sp, // Adjust the font size as desired
                    fontWeight = FontWeight.SemiBold // Use a bold font weight
                ),
                textAlign = TextAlign.Start, // Align text to the left
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            )

            // User name
            Text(
                text = userName,
                style = TextStyle(
                    fontSize = 18.sp, // Adjust the font size as desired
                    fontWeight = FontWeight.Bold // Use a bold font weight
                ),
                textAlign = TextAlign.Start, // Align text to the left
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(220.dp))

            // User apiName
            Text(
                text = AnnotatedString(textValue),
                style = TextStyle(
                    fontSize = 32.sp, // Adjust the font size as desired
                    fontWeight = FontWeight.Bold // Use a bold font weight
                ),
                textAlign = TextAlign.Center, // Align text to the left
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(230.dp))

            // next screen button
            Button(
                onClick = {
                    navController.navigate(Screen.ThirdScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2B637B)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Choose a User", color = Color.White)
            }
        }
    }
}