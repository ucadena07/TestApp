package com.example.testapp.jwt

import com.example.testapp.authentication.UserClaims
import java.util.Base64
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive


fun getUserDetailsFromToken(jwtToken: String) : UserClaims{
    val payload = jwtToken.split('.')[1]
    val jsonBytes = parseBase64WithoutPadding(payload)
    val response = parseJsonToMap(jsonBytes)
    response.token = jwtToken
    return response
}
private fun parseBase64WithoutPadding(payload: String): ByteArray {
    var base64 = ""
    when (payload.length % 4) {
        2 ->  base64 = "$payload=="
        3 ->  base64 = "$payload="
    }
    return Base64.getDecoder().decode(base64)
}

private fun parseJsonElement(jsonElement: JsonElement): Map<String, String> {
    val resultMap = mutableMapOf<String, String>()
    when (jsonElement) {
        is JsonObject -> {
            jsonElement.forEach { (key, value) ->
                if (value is JsonArray) {
                    resultMap[key] = value.joinToString(",") { it.jsonPrimitive.content }
                } else {
                    resultMap[key] = value.jsonPrimitive.content
                }
            }
        }

        else -> {}
    }
    return resultMap
}

private fun parseJsonToMap(jsonBytes: ByteArray): UserClaims {
    val jsonString = jsonBytes.decodeToString()
    val jsonElement: JsonElement = Json.parseToJsonElement(jsonString)
    val y = parseJsonElement(jsonElement)
    val x: MutableMap<String, String> = mutableMapOf()
    for ((key, value) in y) {
        var newKey = ""
        newKey = if (key.contains("http")) {
            val x = key.split("/")
            x[x.count() - 1]
        } else {
            key
        }
        x[newKey] = value
    }

    return UserClaims(
        name = x["name"],
        emailAddress = x["emailaddress"],
        title = x["Title"],
        firstName = x["FirstName"],
        lastName = x["LastName"],
        phoneNumber = x["PhoneNumber"],
        environment = x["Environment"],
        siteUserId = x["SiteUserID"]?.toInt(),
        exp = x["exp"]?.toInt(),
        role = x["role"]?.split(",")
    )
}




