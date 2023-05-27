package com.farizhustha.makanansriwijaya.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.farizhustha.makanansriwijaya.R
import com.farizhustha.makanansriwijaya.ui.theme.Primary
import com.farizhustha.makanansriwijaya.ui.theme.Secondary

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
    ) {
        OutlinedCard(
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = R.drawable.profile,
                    contentDescription = "Photo Profile",
                    modifier = Modifier.size(200.dp),
                )
                Text(
                    stringResource(R.string.profile_name),
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    color = Primary,
                    fontSize = 20.sp
                )
                Text(
                    stringResource(R.string.profile_email),
                    color = Secondary
                )
            }
        }
    }
}