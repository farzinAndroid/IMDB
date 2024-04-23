package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.data.source.GenericPagingSource
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.AddToWatchListResult
import com.farzin.imdb.models.home.Movie
import com.farzin.imdb.models.home.MovieResult
import com.farzin.imdb.models.home.NowPlayingModel
import com.farzin.imdb.models.home.TVModelResult
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.home.WatchListTVResult
import com.farzin.imdb.repository.HomeRepo
import com.farzin.imdb.utils.SourceHelper.loadApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: HomeRepo) : ViewModel() {

    var trendingTVShowsForDay =
        MutableStateFlow<NetworkResult<TrendingTVShowsForDay>>(NetworkResult.Loading())
    var popularTV: Flow<PagingData<TVModelResult>> = flow { emit(PagingData.empty()) }
    var trendingMoviesForWeek:
            Flow<PagingData<MovieResult>> = flow { emit(PagingData.empty()) }
    var tVBasedOnNetwork:
            Flow<PagingData<TVModelResult>> = flow { emit(PagingData.empty()) }
    var nowPlaying = MutableStateFlow<NetworkResult<NowPlayingModel>>(NetworkResult.Loading())
    var addToWatchList =
        MutableStateFlow<NetworkResult<AddToWatchListResult>>(NetworkResult.Loading())
    var watchListTV : Flow<PagingData<WatchListTVResult>> = flow { emit(PagingData.empty()) }
    var watchListMovie =
        MutableStateFlow<NetworkResult<Movie>>(NetworkResult.Loading())
    var movieBasedOnGenre =
        MutableStateFlow<NetworkResult<Movie>>(NetworkResult.Loading())


    fun getAllApiCallsForHome() {

        viewModelScope.launch {

            launch {
                trendingTVShowsForDay.emit(repo.getTVShowsForDay())
            }


            launch {
                popularTV = Pager(
                    PagingConfig(20)
                ) {
                    GenericPagingSource<TVModelResult>(loadPage = { loadParams ->

                        loadApi(
                            loadParams,
                            loadApiRequest = { nextPage ->
                                val response = repo.getPopularTV(page = nextPage).data?.results

                                PagingSource.LoadResult.Page(
                                    data = response ?: emptyList(),
                                    prevKey = null,
                                    nextKey = nextPage + 1
                                )
                            }
                        )
                    })
                }.flow.cachedIn(viewModelScope)
            }


            launch {
                trendingMoviesForWeek = Pager(
                    PagingConfig(20)
                ) {
                    GenericPagingSource(loadPage = { loadParams ->
                        loadApi(
                            loadParams = loadParams,
                            loadApiRequest = { nextPage ->
                                val response =
                                    repo.getTrendingMoviesForWeek(page = nextPage).data?.results

                                PagingSource.LoadResult.Page(
                                    data = response ?: emptyList(),
                                    prevKey = null,
                                    nextKey = nextPage + 1
                                )
                            }
                        )
                    })
                }.flow.cachedIn(viewModelScope)
            }


            launch {
                nowPlaying.emit(repo.getNowPlaying())
            }


        }

    }


    fun getWatchListTV() {
        viewModelScope.launch {
            watchListTV = Pager(
                PagingConfig(20)
            ) {
                GenericPagingSource(loadPage = { loadParams ->
                    loadApi(
                        loadParams = loadParams,
                        loadApiRequest = { nextPage ->
                            val response =
                                repo.getWatchListTV(page = nextPage).data?.results

                            PagingSource.LoadResult.Page(
                                data = response ?: emptyList(),
                                prevKey = null,
                                nextKey = nextPage + 1
                            )
                        }
                    )
                })
            }.flow.cachedIn(viewModelScope)
        }
    }

    fun getWatchListMovie() {
        viewModelScope.launch {

            watchListMovie.emit(repo.getWatchListMovie())

        }
    }

    fun getTvBasedOnNetwork(networkId: Int) {
        viewModelScope.launch {
            tVBasedOnNetwork = Pager(
                PagingConfig(20)
            ) {
                GenericPagingSource(loadPage = { loadParams ->
                    loadApi(
                        loadParams = loadParams,
                        loadApiRequest = { nextPage ->
                            val response = repo.getTVBasedOnNetwork(
                                page = nextPage,
                                netWorkId = networkId
                            ).data?.results

                            PagingSource.LoadResult.Page(
                                data = response ?: emptyList(),
                                prevKey = null,
                                nextKey = nextPage + 1
                            )
                        }
                    )
                })
            }.flow.cachedIn(viewModelScope)
        }
    }

    fun getMoviesBasedOnGenre(genre: String) {
        viewModelScope.launch {

            movieBasedOnGenre.emit(repo.getMoviesBasedOnGenre(genre))


        }
    }


    fun addToWatchList(watchListRequest: AddToWatchListRequest) {
        viewModelScope.launch {
            addToWatchList.emit(repo.addToWatchList(watchListRequest))
        }
    }


}