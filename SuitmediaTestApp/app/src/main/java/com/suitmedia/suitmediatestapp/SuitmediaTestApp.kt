package com.suitmedia.suitmediatestapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suitmedia.suitmediatestapp.ui.session.UserSessionViewModel
import com.suitmedia.suitmediatestapp.ui.navigation.Screen
import com.suitmedia.suitmediatestapp.ui.screen.first.FirstScreen
import com.suitmedia.suitmediatestapp.ui.screen.second.SecondScreen
import com.suitmedia.suitmediatestapp.ui.screen.third.ThirdScreen

@Composable
fun SuitmediaTestApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    userSessionViewModel: UserSessionViewModel = viewModel(),
) {
    Scaffold(
        modifier = modifier
    ){  innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.FirstScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.FirstScreen.route) {
                FirstScreen(
                    navController = navController,
                    userSessionViewModel = userSessionViewModel
                )
            }
            composable(Screen.SecondScreen.route) {
                SecondScreen(
                    navController = navController,
                    userSessionViewModel = userSessionViewModel
                )
            }
            composable(Screen.ThirdScreen.route) {
                ThirdScreen(
                    navController = navController,
                    userSessionViewModel = userSessionViewModel
                )
            }
        }
    }
}