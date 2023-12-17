package com.farzin.imdb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.database.FavoriteDBModel
import com.farzin.imdb.models.database.PersonDBModel
import com.farzin.imdb.models.profile.AccountDetail
import com.farzin.imdb.models.profile.RequestToken
import com.farzin.imdb.models.profile.SessionId
import com.farzin.imdb.repository.ProfileRepo
import com.farzin.imdb.ui.screens.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: ProfileRepo) : ViewModel() {


    var screenState by mutableStateOf(ProfileState.NOTLOGGED)

    val initialRequestToken = MutableStateFlow<NetworkResult<RequestToken>>(NetworkResult.Loading())
    val sessionId = MutableStateFlow<NetworkResult<SessionId>>(NetworkResult.Loading())
    val accountDetail = MutableStateFlow<NetworkResult<AccountDetail>>(NetworkResult.Loading())
    val allPerson = repo.allPerson
    val allFavorites = repo.allFavorites


    fun getInitialRequestToken() {

        viewModelScope.launch {
            initialRequestToken.emit(repo.getInitialRequestToken())
        }

    }


    fun getSessionId(userName: String, password: String, requestToken: String) {

        viewModelScope.launch {

            val loginDeferred =
                async { repo.getLoginRequestToken(userName, password, requestToken) }

            val sessionIdDeferred =
                async { repo.getSessionId(loginDeferred.await().data?.request_token ?: "") }
            sessionId.emit(sessionIdDeferred.await())

            launch {
                accountDetail.emit(repo.getAccountDetails(sessionId.value.data?.session_id ?: ""))
            }
        }

    }


    fun addPerson(person: PersonDBModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addPerson(person)
        }
    }

    fun removePerson(person: PersonDBModel){
        viewModelScope.launch(Dispatchers.IO) {
            repo.removePerson(person)
        }
    }


    fun getPersonId(id:Int) : Int{
        return repo.getPersonId(id)
    }



    fun addFavorite(favorite: FavoriteDBModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addFavorites(favorite)
        }
    }

    fun removeFavorite(favorite: FavoriteDBModel){
        viewModelScope.launch(Dispatchers.IO) {
            repo.removeFavorites(favorite)
        }
    }


    fun getFavoriteId(id:Int) = repo.getFavoriteId(id)


}