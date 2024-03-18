package com.example.testapp.screens.accountDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.size.Size
import com.example.testapp.authentication.AuthState
import com.example.testapp.layout.MainLayout
import com.example.testapp.utils.formatDate
import com.example.testapp.utils.formatDateTime

@Composable
fun AccountDetailsScreen(navController: NavController) {
    MainLayout(navController = navController) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "user account icon",
                modifier = Modifier.size(60.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(text = AuthState.getAuthDetails()?.name ?: "", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
            Text(text = AuthState.getAuthDetails()?.emailAddress ?: "", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
            Text(text = AuthState.getAuthDetails()?.phoneNumber ?: "", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
            Text(text = "Token Exp: ${formatDate(AuthState.getAuthDetails()?.exp!!)} @ ${formatDateTime(AuthState.getAuthDetails()?.exp!!)}", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
            Text(text = "Env: ${AuthState.getAuthDetails()?.environment}", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(20.dp)) {
                Text(text = "Sign Out")
            }

        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun DetailsPreview(){
//    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
//        .fillMaxSize()
//        .padding(top = 30.dp)) {
//        Icon(
//            imageVector = Icons.Filled.AccountCircle,
//            contentDescription = "user account icon",
//            modifier = Modifier.size(60.dp),
//            tint = MaterialTheme.colorScheme.secondary
//        )
//        Text(text = "Ulises Cadena", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
//        Text(text = "ucadena07@gmail.com", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
//        Text(text = "608-860-0648", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
//        Text(text = "Token Exp: ", modifier = Modifier.padding(3.dp), fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Clip)
//        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(20.dp)) {
//            Text(text = "Sign Out")
//        }
//
//
//    }
//}