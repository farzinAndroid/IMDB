package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.SearchApiInterface
import javax.inject.Inject

class SearchRepo @Inject constructor(private val api: SearchApiInterface) : BaseApiResponse() {


//    suspend fun getInitialRequestToken() =
//        safeApiCall {
//
//        }


}