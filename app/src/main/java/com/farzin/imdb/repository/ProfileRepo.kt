package com.farzin.imdb.repository

import com.farzin.imdb.data.database.PersonDao
import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.ProfileApiInterface
import com.farzin.imdb.models.database.PersonDBModel
import javax.inject.Inject

class ProfileRepo @Inject constructor(
    private val api: ProfileApiInterface,
    private val dao:PersonDao
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

    val allPerson = dao.getAllPersons()

    suspend fun removePerson(person: PersonDBModel) = dao.removePerson(person)

    suspend fun addPerson(person: PersonDBModel) = dao.addPerson(person)

    fun getId(id:Int) : Int = dao.getId(id)

}