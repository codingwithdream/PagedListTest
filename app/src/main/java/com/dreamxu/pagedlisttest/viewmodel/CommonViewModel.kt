package com.dreamxu.pagedlisttest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dreamxu.pagedlisttest.room.User
import com.dreamxu.pagedlisttest.room.UserDatabase

class CommonViewModel(application: Application): AndroidViewModel(application) {
    private val userDao = UserDatabase.getDatabase(application).userDao()

    fun getRefreshLiveData(): LiveData<PagedList<User>> =
        LivePagedListBuilder(userDao.loadAllUsers(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()).build()

    companion object {
        private const val PAGE_SIZE = 15
        private const val ENABLE_PLACEHOLDERS = false
    }

    class CommonViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CommonViewModel(app) as T
        }
    }
}
