package com.dreamxu.pagedlisttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.dreamxu.pagedlisttest.activity.BasicUsageActivity
import com.dreamxu.pagedlisttest.activity.HeaderProxyActivity
import com.dreamxu.pagedlisttest.activity.UpdateUserActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        update_user_data.setOnClickListener {
            startActivity(Intent(this, UpdateUserActivity::class.java))
        }

        basic_usage.setOnClickListener {
            startActivity(Intent(this, BasicUsageActivity::class.java))
        }

        header_proxy.setOnClickListener {
            startActivity(Intent(this, HeaderProxyActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
