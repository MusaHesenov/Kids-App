package com.example.kidsapp.data

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String = "",
    val age: String = "",
    val imagePath: String = ""
) {
    constructor(): this("", "", "", "", "", "")
}