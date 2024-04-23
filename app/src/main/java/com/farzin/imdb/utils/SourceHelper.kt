package com.farzin.imdb.utils

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.farzin.imdb.models.home.TVModelResult
import kotlinx.coroutines.flow.flowOf

object SourceHelper {

    suspend fun <Value : Any> loadApi(
        loadParams: PagingSource.LoadParams<Int>,
        loadApiRequest:suspend (nextPage:Int)-> PagingSource.LoadResult<Int, Value>
    ): PagingSource.LoadResult<Int, Value>{
        return try {
            val nextPage = loadParams.key ?: 1
            loadApiRequest(nextPage)

        }catch (e:Exception){
            Log.e("TAG", "error $e")
            PagingSource.LoadResult.Error(e)
        }
    }

    val emptyPagingData = flowOf(PagingData.from(emptyList<TVModelResult>()))

}