package com.example.testapp.screens.buyerScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapp.components.shared.NkTextField
import com.example.testapp.layout.MainLayout

@Composable
fun BuyerFormScreen(vm: BuyerFormViewModel, navController: NavHostController) {
    if(vm.buyerDTO.value != null){
        MainLayout(navController = navController) {
            Column(modifier = Modifier.padding(6.dp)) {
                Text(text = "Buyer form screen ID: ${vm.buyerDTO.value?.details?.buyerName}")
                NkTextField(label = "Name", value = vm.buyerDTO.value?.details?.buyerName)
            }
  
        }

    }

}