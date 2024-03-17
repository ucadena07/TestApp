package com.example.testapp.model

import com.example.testapp.utils.formatApiDateToTimestamp
import java.util.Date

data class Buyer(
    val buyerID: Int,
    val buyerName: String,
    val createdDate: String?,
    val groupName:String,
    val address1: String
    ){

}
