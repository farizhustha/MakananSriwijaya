package com.farizhustha.makanansriwijaya

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.farizhustha.makanansriwijaya.ui.navigation.Screen
import com.farizhustha.makanansriwijaya.ui.screen.detail.DetailScreen
import com.farizhustha.makanansriwijaya.ui.screen.home.HomeScreen
import com.farizhustha.makanansriwijaya.ui.screen.profile.ProfileScreen
import com.farizhustha.makanansriwijaya.ui.theme.Primary
import com.farizhustha.makanansriwijaya.ui.theme.Secondary

@Composable
fun MakananSriwijayaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier,
        topBar = {
            if (currentRoute != Screen.Detail.route) {
                TopBar(navController = navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController, startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.StringType }),

                ) {
                val id = it.arguments?.getString("id") ?: ""
                DetailScreen(id = id,
                    navigateBack = {
                        navController.navigateUp()
                    })
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = Primary,
            containerColor = Secondary,
            actionIconContentColor = Primary
        ),
        title = {
            Text(
                stringResource(R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        navController.navigate(Screen.Profile.route) {
                            launchSingleTop = true
                        }
                    }
            )

        },
        modifier = modifier.shadow(10.dp)
    )
}