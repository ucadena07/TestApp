package com.example.testapp.components.buyers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testapp.model.Buyer
import com.example.testapp.utils.formatApiDateToTimestamp

@Composable
//@Preview(showBackground = true)
fun BuyerCard(buyer: Buyer, onClick: () -> Unit = {}){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onClick() }
        , colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {

        Column(modifier = Modifier.padding(6.dp)) {
            Text(text = "Buyer Name: ${buyer.buyerName}",style = MaterialTheme.typography.titleSmall)
            Text(text = "Address: ${buyer.address1}",style = MaterialTheme.typography.titleSmall)
            Text(text = "Group: ${buyer.groupName}",style = MaterialTheme.typography.titleSmall)
            Text(text = "Created Date: ${formatApiDateToTimestamp(buyer.createdDate)}",style = MaterialTheme.typography.titleSmall )
        }

    }
}