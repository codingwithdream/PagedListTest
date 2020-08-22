package com.dreamxu.pagedlisttest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dreamxu.pagedlisttest.MyApplication
import com.dreamxu.pagedlisttest.R
import com.dreamxu.pagedlisttest.room.User
import com.dreamxu.pagedlisttest.room.UserDatabase
import kotlinx.android.synthetic.main.activity_update_user.*
import kotlin.concurrent.thread

class UpdateUserActivity : AppCompatActivity() {
    private val TAG = "UpdateUserActivity"

    private val userDao = UserDatabase.getDatabase(MyApplication.context).userDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        add_user.setOnClickListener {
            thread {
                val user = User("Dream", "Xu")
                userDao.insertUser(user)
                Log.d(TAG, "Insert an user")
            }
        }

        update_user.setOnClickListener {
            thread {
                val userList = userDao.findUserByLastName("Manger")
                val updateUser: User = userList[0]
                updateUser.lastName = "Munger"
                userDao.updateUser(updateUser)
                Log.d(TAG, "Update user, find user by lastName, the number is ${userList.size}")
            }
        }

        delete_user.setOnClickListener {
            thread {
                val userList = userDao.findUserByLastName("Xu")
                if (userList.isNotEmpty()){
                    val deleteUser: User = userList[0]
                    userDao.deleteUser(deleteUser)
                    Log.d(TAG, "Delete user, find user by lastName, the number is ${userList.size}")
                } else {
                    Log.d(TAG, "Deleted user is not exist")
                }

            }
        }
    }
}
