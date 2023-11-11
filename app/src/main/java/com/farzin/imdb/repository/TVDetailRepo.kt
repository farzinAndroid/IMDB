package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.TVDetailApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.tvDetail.AddRating
import com.farzin.imdb.models.tvDetail.AddRatingModel
import com.farzin.imdb.models.tvDetail.CastAndCrewModelTV
import com.farzin.imdb.models.tvDetail.ImagesTVModel
import com.farzin.imdb.models.tvDetail.RatedTVModel
import com.farzin.imdb.models.tvDetail.TVDetailModel
import com.farzin.imdb.models.tvDetail.TVReviewModel
import javax.inject.Inject

class TVDetailRepo @Inject constructor(private val api: TVDetailApiInterface) : BaseApiResponse() {


    suspend fun getTVDetails(seriesId:Int) : NetworkResult<TVDetailModel> =
        safeApiCall {
            api.getTVDetails(seriesId)
        }


    suspend fun getRatedTV() : NetworkResult<RatedTVModel> =
        safeApiCall {
            api.getRatedTV()
        }


    suspend fun addRating(seriesId:Int,rating:AddRating) : NetworkResult<AddRatingModel> =
        safeApiCall {
            api.addRating(seriesId = seriesId, rating = rating)
        }


    suspend fun getTVCastAndCrew(seriesId:Int) : NetworkResult<CastAndCrewModelTV> =
        safeApiCall {
            api.getTVCastAndCrew(seriesId)
        }


    suspend fun getRecommendedTVShows(seriesId:Int) : NetworkResult<TrendingTVShowsForDay> =
        safeApiCall {
            api.getRecommendedTVShows(seriesId)
        }

    suspend fun getImagesForTV(seriesId:Int) : NetworkResult<ImagesTVModel> =
        safeApiCall {
            api.getImagesForTV(seriesId)
        }

    suspend fun getReviewsForTV(seriesId:Int,page:Int) : NetworkResult<TVReviewModel> =
        safeApiCall {
            api.getReviewsForTV(seriesId, page = page)
        }


}