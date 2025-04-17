package com.example.filmsdataapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.filmsdataapp.R
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.TextColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationMenuWrapper(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        modifier = Modifier.background(color = BackGroundColor),
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(330.dp), drawerContainerColor = BackGroundColor) {
                Column(modifier = Modifier.padding(0.dp)) {
                    Text("Menu", color = TextColor, modifier = Modifier.padding(15.dp, 20.dp, 15.dp), fontSize = 20.sp)
                    Divider(modifier = Modifier.padding(15.dp, 10.dp, 15.dp, 5.dp))
                    Row(){
                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = null,
                            modifier = Modifier
                                .width(50.dp)
                                .height(20.dp)
                        )
                        NavigationDrawerItem(
                            label = { Text("Main page",  color = Color.White) },
                            selected = false,
                            onClick = {
                                scope.launch { navController.navigate("main_screen")
                                    drawerState.close()
                                }

                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color(0xFF333333),
                                unselectedContainerColor = Color.Transparent,

                                ),
                            modifier = Modifier.padding(0.dp)


                        )
                    }

                    NavigationDrawerItem(
                        label = { Text("Movies", color = TextColor)},
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigate("movies_screen")
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFF333333),
                            unselectedContainerColor = Color.Transparent,
                        )
                    )
                    NavigationDrawerItem(
                        label = { Text("TV Shows", color = TextColor)},
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigate("tvshows_screen")
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFF333333),
                            unselectedContainerColor = Color.Transparent,
                        )
                    )
                    NavigationDrawerItem(
                        label = { Text("Actors", color = TextColor)},
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigate("actors_screen")
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFF333333),
                            unselectedContainerColor = Color.Transparent,
                        )
                    )
                    NavigationDrawerItem(
                        label = { Text("Reviews", color = TextColor)},
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigate("reviews_screen")
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFF333333),
                            unselectedContainerColor = Color.Transparent,
                        )
                    )

                    NavigationDrawerItem(
                        label = { Text("About program", color = TextColor)},
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigate("about_program_screen")
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFF333333),
                            unselectedContainerColor = Color.Transparent,
                        )
                    )
                    NavigationDrawerItem(
                        label = { Text("Contacts", color = TextColor)},
                        selected = false,
                        onClick = {
                            scope.launch {
                                 navController.navigate("contacts_screen")
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFF333333),
                            unselectedContainerColor = Color.Transparent,
                        )
                    )
                }
            }
        }
    ) {
        content()
    }
}