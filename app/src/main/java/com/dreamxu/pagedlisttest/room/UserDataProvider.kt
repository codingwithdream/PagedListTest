package com.dreamxu.pagedlisttest.room

import android.util.Log
import com.dreamxu.pagedlisttest.MyApplication
import com.dreamxu.pagedlisttest.util.JsonUtil
import javax.sql.DataSource

class UserDataProvider {
    private val TAG = "UserDataProvider"
    private val userDao = UserDatabase.getDatabase(MyApplication.context).userDao()

    fun addUser(user: User) {
        userDao.insertUser(user)
        Log.d(TAG, user.toString())
    }

    fun insertUsers() {
        val contactList = JsonUtil.getContacts()
        var user: User
        Log.d(TAG, "insert users")
        for (contact in contactList){
            user = User(contact.firstName, contact.lastName)
            userDao.insertUser(user)
        }
    }

}