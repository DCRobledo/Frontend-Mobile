package com.example.profilecardlayout

data class UserProfile(val name: String, val isActive: Boolean, val drawableId: Int)

val userProfileList = arrayListOf(
    UserProfile("Finn the Human", true, R.drawable.finn),
    UserProfile("Jake the Dog", false, R.drawable.jake),
)