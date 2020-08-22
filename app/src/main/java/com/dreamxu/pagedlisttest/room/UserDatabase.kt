package com.dreamxu.pagedlisttest.room

import android.content.Context
import android.os.Build
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dreamxu.pagedlisttest.ioThread
import com.dreamxu.pagedlisttest.util.JsonUtil

@Database(version = 1, entities = [User::class])
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): UserDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user_database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        ioThread {
                            val contactList = JsonUtil.getContacts()
                            var user: User
                            for (contact in contactList) {
                                user = User(contact.firstName, contact.lastName)
                                user.id =  getDatabase(context).userDao().insertUser(user)
                            }
                        }
                    }
                })
                .build()
                .apply {
                instance = this
            }
        }
    }
}