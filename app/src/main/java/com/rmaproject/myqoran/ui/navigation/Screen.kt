package com.rmaproject.myqoran.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Settings : Screen("Settings")
    object ReadQoran :
        Screen("Read?&indexType={indexType}&surahNumber={surahNumber}&juzNumber={juzNumber}&pageNumber={pageNumber}") {
        fun createRoute(
            indexType: Int,
            surahNumber: Int?,
            juzNumber: Int?,
            pageNumber: Int?
        ): String {
            return "Read?indexType=${indexType}&surahNumber=${surahNumber}&juzNumber=${juzNumber}&pageNumber=${pageNumber}"
        }
    }
}

class MyQoranNavigationActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSettings: () -> Unit = {
        navController.navigate(Screen.Settings.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}