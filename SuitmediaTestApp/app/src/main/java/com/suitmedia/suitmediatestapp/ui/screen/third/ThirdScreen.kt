package com.suitmedia.suitmediatestapp.ui.screen.third

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.suitmedia.suitmediatestapp.response.RegresInItem
import com.suitmedia.suitmediatestapp.ui.collection.UserCollectionItem
import com.suitmedia.suitmediatestapp.ui.session.UserSessionViewModel
import kotlinx.coroutines.launch

@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userSessionViewModel: UserSessionViewModel
) {
    val apiName: String by userSessionViewModel.apiName.observeAsState("")

    val thirdScreenViewModel : ThirdScreenViewModel = viewModel()
    val result: List<RegresInItem?>? by thirdScreenViewModel.result.observeAsState()
    val isLoading by thirdScreenViewModel.isLoading.observeAsState(false)

    val showApiNamePopup = remember { mutableStateOf(false) }

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Fetch initial items when the screen is first shown
    LaunchedEffect(Unit) {
        thirdScreenViewModel.getUserCollection(page = 1, perPage = 15)
    }

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
                        text = "Third Screen",
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

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                state = lazyListState,
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 64.dp)
            ) {
                items(items = result ?: emptyList()) { item ->
                    // Display each item in the list
                    item.let { regresInItem ->
                        // Display the item content
                        if (regresInItem != null) {
                            UserCollectionItem(
                                userCollectionData = regresInItem,
                                onItemClick = {
                                    userSessionViewModel.setApiName(
                                        "${regresInItem.firstName} ${regresInItem.lastName}"
                                    )
                                    showApiNamePopup.value = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showApiNamePopup.value) {
        AlertDialog(
            onDismissRequest = { showApiNamePopup.value = false },
            title = { Text(text = "Palindrome Result") },
            text = {
                Text(text = "$apiName has been chosen")
            },
            confirmButton = {
                Button(
                    onClick = { showApiNamePopup.value = false },
                    content = { Text(text = "OK") }
                )
            }
        )
    }
}