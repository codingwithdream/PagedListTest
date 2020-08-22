package com.dreamxu.pagedlisttest.util

import android.content.res.AssetManager
import com.dreamxu.pagedlisttest.MyApplication
import com.dreamxu.pagedlisttest.model.ContactPerson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

object JsonUtil {
    private val contactList = ArrayList<ContactPerson>()
    private val jsonFileName = "contacts.json"

    fun getContacts(): List<ContactPerson> {
        val typeOf = object : TypeToken<List<ContactPerson>>() {}.type
        val jsonData = parseJsonData()
        val contactsData = Gson().fromJson<List<ContactPerson>>(jsonData, typeOf)
        for (contact in contactsData) {
            contactList.add(contact)
        }
        return contactList
    }

    private fun parseJsonData(): String {
        val stringBuilder = StringBuilder()

        try {
            val assetManager: AssetManager = MyApplication.context.assets
            val inputStreamReader = InputStreamReader(assetManager.open(jsonFileName))
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String? = bufferedReader.readLine()
            while (line != null ) {
                stringBuilder.append(line)
                line = bufferedReader.readLine()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}