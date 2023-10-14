package com.farzin.imdb.data.datastore

interface DataStoreRepo {

    suspend fun putString(value:String,key:String)
    suspend fun getString(key: String) : String?
    suspend fun putBoolean(value:Boolean,key:String)
    suspend fun getBoolean(key: String) : Boolean?

}