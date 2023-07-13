package com.suitmedia.suitmediatestapp.ui.screen.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.suitmedia.suitmediatestapp.R
import com.suitmedia.suitmediatestapp.ui.navigation.Screen
import com.suitmedia.suitmediatestapp.ui.session.UserSessionViewModel

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userSessionViewModel: UserSessionViewModel
) {
    val nameState = remember { mutableStateOf("") }
    val palindromeState = remember { mutableStateOf("") }

    val firstScreenViewModel : FirstScreenViewModel = viewModel()

    val isPalindrome = remember { mutableStateOf(false) }
    val showPalindromePopup = remember { mutableStateOf(false) }

    val showPalindromeErrorPopup = remember { mutableStateOf(false) }
    val showNameErrorPopup = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.first_screen_background),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.first_screen_icon),
                    contentDescription = "First Screen Icon",
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            // Name Text Field
            TextField(
                value = nameState.value,
                onValueChange = { nameState.value = it },
                label = { Text("Name", color = Color(0xFF687775C)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    leadingIconColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Palindrome Text Field
            TextField(
                value = palindromeState.value,
                onValueChange = { palindromeState.value = it },
                label = { Text("Palindrome", color = Color(0xFF687775C)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    leadingIconColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )



            // palindrome button
            Button(
                onClick = {
                    val palindrome = palindromeState.value
                    if (palindrome != "") {
                        // check palindrome or not
                        isPalindrome.value = firstScreenViewModel.checkPalindrome(palindrome)
                        showPalindromePopup.value = true
                    } else {
                        showPalindromeErrorPopup.value = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2B637B)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Text(text = "CHECK", color = Color.White)
            }

            // next screen button
            Button(
                onClick = {
                    val name = nameState.value
                    if (name != "") {
                        userSessionViewModel.setUserName(name)
                        // switch to next screen
                        navController.navigate(Screen.SecondScreen.route)
                    } else {
                        showNameErrorPopup.value = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2B637B)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                Text(text = "NEXT", color = Color.White)
            }
        }
    }

    if (showPalindromePopup.value) {
        AlertDialog(
            onDismissRequest = { showPalindromePopup.value = false },
            title = { Text(text = "Palindrome Result") },
            text = {
                if (isPalindrome.value) {
                    Text(text = "${palindromeState.value} is a palindrome")
                } else {
                    Text(text = "${palindromeState.value} is NOT a palindrome")
                }
            },
            confirmButton = {
                Button(
                    onClick = { showPalindromePopup.value = false },
                    content = { Text(text = "OK") }
                )
            }
        )
    }

    if (showPalindromeErrorPopup.value) {
        AlertDialog(
            onDismissRequest = { showPalindromeErrorPopup.value = false },
            title = { Text(text = "Palindrome Error") },
            text = { Text(text = "Sentence must not be empty") },
            confirmButton = {
                Button(
                    onClick = { showPalindromeErrorPopup.value = false },
                    content = { Text(text = "OK") }
                )
            }
        )
    }

    if (showNameErrorPopup.value) {
        AlertDialog(
            onDismissRequest = { showNameErrorPopup.value = false },
            title = { Text(text = "Name Error") },
            text = { Text(text = "Name must not be empty") },
            confirmButton = {
                Button(
                    onClick = { showNameErrorPopup.value = false },
                    content = { Text(text = "OK") }
                )
            }
        )
    }
}