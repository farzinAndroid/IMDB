package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.farzin.imdb.data.source.GenericPagingSource
import com.farzin.imdb.models.home.MovieResult
import com.farzin.imdb.models.home.TrendingTVShowsForDayResult
import com.farzin.imdb.repository.SearchRepo
import com.farzin.imdb.utils.SourceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: SearchRepo) : ViewModel() {

    private val _searchedMovie = MutableStateFlow<PagingData<MovieResult>>(PagingData.empty())
    val searchedMovie: StateFlow<PagingData<MovieResult>> = _searchedMovie.asStateFlow()

    private val _searchedTV = MutableStateFlow<PagingData<TrendingTVShowsForDayResult>>(PagingData.empty())
    val searchedTV: StateFlow<PagingData<TrendingTVShowsForDayResult>> = _searchedTV.asStateFlow()


    fun searchMovie(query: String) {
        viewModelScope.launch {
            val newPager = Pager(
                PagingConfig(20)
            ) {
                GenericPagingSource<MovieResult>(loadPage = { loadParams ->

                    SourceHelper.loadApi(
                        loadParams,
                        loadApiRequest = { nextPage ->
                            val response = repo.searchMovie(page = nextPage, query = query).data?.results

                            PagingSource.LoadResult.Page(
                                data = response ?: emptyList(),
                                prevKey = null,
                                nextKey = nextPage + 1
                            )
                        }
                    )
                })
            }.flow.cachedIn(viewModelScope)

            newPager.collectLatest {
                _searchedMovie.value = it
            }
        }
    }


    fun searchTV(query: String) {
        viewModelScope.launch {
            val newPager = Pager(
                PagingConfig(20)
            ) {
                GenericPagingSource<TrendingTVShowsForDayResult>(loadPage = { loadParams ->

                    SourceHelper.loadApi(
                        loadParams,
                        loadApiRequest = { nextPage ->
                            val response = repo.searchTV(page = nextPage, query = query).data?.results

                            PagingSource.LoadResult.Page(
                                data = response ?: emptyList(),
                                prevKey = null,
                                nextKey = nextPage + 1
                            )
                        }
                    )
                })
            }.flow.cachedIn(viewModelScope)

            newPager.collectLatest {
                _searchedTV.value = it
            }
        }
    }


}