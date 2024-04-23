package com.farzin.imdb.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState

class GenericPagingSource<Value : Any>(
    private val loadPage: suspend (LoadParams<Int>) -> LoadResult<Int, Value>
) : PagingSource<Int, Value>() {
    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val pageAnchor = state.closestPageToPosition(anchorPosition)
            pageAnchor?.prevKey?.plus(1) ?: pageAnchor?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return loadPage(params)
    }
}