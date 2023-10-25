package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.datastore.DataStoreRepoImpl
import com.farzin.imdb.utils.Constants.ENGLISH
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
        const val ACCOUNT_ID = "ACCOUNT_ID"
        const val USER_NAME = "USER_NAME"
        const val USER_LANG = "USER_LANG"
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

    fun saveAccountId(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.putString(value, ACCOUNT_ID)
        }
    }


    fun saveUserName(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.putString(value, USER_NAME)
        }
    }

    fun saveUserLang(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.putString(value, USER_LANG)
        }
    }

    fun getSessionId(): String? = runBlocking {
        repo.getString(SESSION_ID)
    }


    fun getServiceId(): Int? = runBlocking {
        repo.getInt(SERVICE_ID)
    }

    fun getAccountId(): String? = runBlocking {
        repo.getString(ACCOUNT_ID)
    }

    fun getUserName(): String? = runBlocking {
        repo.getString(USER_NAME)
    }

    fun getUserLang(): String = runBlocking {
        repo.getString(USER_LANG) ?: ENGLISH
    }

}