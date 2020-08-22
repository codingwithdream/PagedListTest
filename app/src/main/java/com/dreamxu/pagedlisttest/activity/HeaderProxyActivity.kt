package com.dreamxu.pagedlisttest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dreamxu.pagedlisttest.R
import com.dreamxu.pagedlisttest.adapter.HeaderProxyAdapter
import com.dreamxu.pagedlisttest.viewmodel.CommonViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HeaderProxyActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val TAG = "HeaderProxyActivity"

    private lateinit var mCommonViewModel: CommonViewModel
    private lateinit var mHeaderProxyAdapter: HeaderProxyAdapter
    private lateinit var mHeaderProxyRecycleView: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_proxy)

        mHeaderProxyAdapter = HeaderProxyAdapter()
        mHeaderProxyRecycleView = findViewById(R.id.header_proxy_recycleview)
        mSwipeRefreshLayout = findViewById(R.id.user_refresh)
        mSwipeRefreshLayout.setOnRefreshListener(this)

        mHeaderProxyRecycleView.layoutManager = LinearLayoutManager(this)
        mHeaderProxyRecycleView.adapter = mHeaderProxyAdapter

        val viewModelFactory = CommonViewModel.CommonViewModelFactory(application)
        mCommonViewModel = ViewModelProvider(this, viewModelFactory ).get(CommonViewModel::class.java)
        mCommonViewModel.getRefreshLiveData().observe(this, Observer {
            mHeaderProxyAdapter.submitList(it)
        })
    }

    // Simulate Refresh
    override fun onRefresh() {
        mCommonViewModel.getRefreshLiveData().observe(this, Observer {
            mHeaderProxyAdapter.submitList(it)
        })
        mSwipeRefreshLayout.isRefreshing = false
    }
}
