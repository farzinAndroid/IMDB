package com.farzin.imdb.repository

import com.farzin.imdb.data.database.FavoriteDao
import com.farzin.imdb.data.database.PersonDao
import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.ProfileApiInterface
import com.farzin.imdb.models.database.FavoriteDBModel
import com.farzin.imdb.models.database.PersonDBModel
import javax.inject.Inject

class ProfileRepo @Inject constructor(
    private val api: ProfileApiInterface,
    private val personDao:PersonDao,
    private val favoriteDao: FavoriteDao
) : BaseApiResponse() {


    suspend fun getInitialRequestToken() =
        safeApiCall {
            api.getInitialRequestToken()
        }


    suspend fun getLoginRequestToken(userName: String, password: String, requestToken: String) =
        safeApiCall {
            api.getLoginRequestToken(
                username = userName,
                password = password,
                requestToken = requestToken
            )
        }

    suspend fun getSessionId(requestToken: String) =
        safeApiCall {
            api.getSessionId(requestToken = requestToken)
        }


    suspend fun getAccountDetails(sessionId:String) =
        safeApiCall {
            api.getAccountDetails(sessionId=sessionId)
        }

    val allPerson = personDao.getAllPersons()

    suspend fun removePerson(person: PersonDBModel) = personDao.removePerson(person)

    suspend fun addPerson(person: PersonDBModel) = personDao.addPerson(person)

    fun getPersonId(id:Int) : Int = personDao.getId(id)




    val allFavorites = favoriteDao.getAllFavorites()

    suspend fun removeFavorites(favorite: FavoriteDBModel) = favoriteDao.removeFavorite(favorite)

    suspend fun addFavorites(favorite: FavoriteDBModel) = favoriteDao.addFavorite(favorite)

    fun getFavoriteId(id:Int) : Int = favoriteDao.getId(id)

}