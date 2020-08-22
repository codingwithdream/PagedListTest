package com.dreamxu.pagedlisttest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dreamxu.pagedlisttest.room.User
import com.dreamxu.pagedlisttest.room.UserDao
import com.dreamxu.pagedlisttest.room.UserDatabase
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var userDao: UserDao
    private lateinit var userDB: UserDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        userDB = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java).build()
        userDao = userDB.userDao()
    }

    @After
    fun closeDb() {
        try {
            userDB.close()
        } catch (e: IOException) {

        }
    }

    @Test
    fun writeUserAndReadList() {
        try {
            val user = User("dream", "xu")
            userDao.insertUser(user)
            val userByLastName: List<User> = userDao.findUserByLastName("xu")
            assertThat(userByLastName[0], equalTo(user))
        } catch (e: Exception) {
            print(e.stackTrace)
        }
    }
}
