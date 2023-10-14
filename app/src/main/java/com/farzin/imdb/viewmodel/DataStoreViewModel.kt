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
        const val IS_LOGGED_IN = "IS_LOGGED_IN"
    }


    fun saveSessionId(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.putString(value, SESSION_ID)
        }
    }

    fun saveLoginState(value: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.putBoolean(value, IS_LOGGED_IN)
        }
    }

    fun getSessionId(): String? = runBlocking {
        repo.getString(SESSION_ID)
    }

    fun getLoginState(): Boolean? = runBlocking {
        repo.getBoolean(IS_LOGGED_IN)
    }

}