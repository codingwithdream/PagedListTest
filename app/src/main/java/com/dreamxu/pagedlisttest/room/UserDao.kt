package com.dreamxu.pagedlisttest.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Insert
    fun insertUser(users: List<User>)

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): DataSource.Factory<Int, User>

    @Query("delete from User")
    fun deleteAll()

    @Query("select * from User where lastName = :lastName")
    fun findUserByLastName(lastName: String): List<User>

    @Delete
    fun deleteUser(user: User)
}
