package com.dreamxu.pagedlisttest.model

import com.google.gson.annotations.SerializedName

data class ContactPerson(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar_filename")
    val avatarFileName: String,
    val title: String,
    val introduction: String
)