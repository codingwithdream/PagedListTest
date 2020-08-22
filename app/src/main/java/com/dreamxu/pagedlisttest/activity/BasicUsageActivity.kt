package com.dreamxu.pagedlisttest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamxu.pagedlisttest.R
import com.dreamxu.pagedlisttest.adapter.BasicUsageAdapter
import com.dreamxu.pagedlisttest.viewmodel.CommonViewModel

class BasicUsageActivity : AppCompatActivity() {
    private val TAG = "BasicUsageActivity"

    private lateinit var mBasicUsageAdapter: BasicUsageAdapter
    private lateinit var mBasicUsageRecyclerView: RecyclerView
    private lateinit var mBasicUsageViewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_usgae)

        mBasicUsageAdapter = BasicUsageAdapter()
        mBasicUsageRecyclerView = findViewById(R.id.basic_usage_recycleview)
        mBasicUsageRecyclerView.layoutManager = LinearLayoutManager(this)
        mBasicUsageRecyclerView.adapter = mBasicUsageAdapter

        mBasicUsageViewModel = ViewModelProvider(this, CommonViewModel.CommonViewModelFactory(application))
            .get(CommonViewModel::class.java)
        mBasicUsageViewModel.getRefreshLiveData().observe(this, Observer {
            mBasicUsageAdapter.submitList(it)
        })
    }
}