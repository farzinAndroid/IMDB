package com.farzin.imdb.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.farzin.imdb.utils.Constants.DATASTORE_NAME
import kotlinx.coroutines.flow.first
import javax.inject.Inject

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)
class DataStoreRepoImpl @Inject constructor(private val context: Context) : DataStoreRepo {


    override suspend fun putString(value: String, key: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { it[preferenceKey] = value }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()

            preferences[preferenceKey]

        }catch (e:Exception){
            Log.e("TAG","data store error : ${e.printStackTrace()}")
            null
        }
    }

    override suspend fun putBoolean(value: Boolean, key: String) {
        val preferenceKey = booleanPreferencesKey(key)
        context.dataStore.edit { it[preferenceKey] = value }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return try {
            val preferenceKey = booleanPreferencesKey(key)
            val preferences = context.dataStore.data.first()

            preferences[preferenceKey]

        }catch (e:Exception){
            Log.e("TAG","data store error : ${e.printStackTrace()}")
            null
        }
    }
}