package com.dreamxu.pagedlisttest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val firstName: String,
    var lastName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
