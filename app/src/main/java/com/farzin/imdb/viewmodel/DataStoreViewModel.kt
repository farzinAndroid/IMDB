package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.datastore.DataStoreRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repo: DataStoreRepoImpl) : ViewModel() {

    companion object {
        const val SESSION_ID = "SESSION_ID"
        const val SERVICE_ID = "SERVICE_ID"
    }


    fun saveSessionId(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.putString(value, SESSION_ID)
        }
    }


    fun saveServiceId(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.putInt(value, SERVICE_ID)
        }
    }

    fun getSessionId(): String? = runBlocking {
        repo.getString(SESSION_ID)
    }


    fun getServiceId(): Int? = runBlocking {
        repo.getInt(SERVICE_ID)
    }

}