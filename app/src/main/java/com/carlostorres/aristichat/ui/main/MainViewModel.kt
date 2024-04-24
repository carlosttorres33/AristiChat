package com.carlostorres.aristichat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.aristichat.domain.GetUserNameUseCase
import com.carlostorres.aristichat.domain.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
): ViewModel() {

    init {

        verifyUserLogged()

    }

    private var _uiState = MutableStateFlow<MainViewState>(MainViewState.LOADING)
    val uiState : StateFlow<MainViewState> = _uiState

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val name = getUserNameUseCase()

            if (name.isNotEmpty()){
                _uiState.value = MainViewState.REGISTERED
            } else {
                _uiState.value = MainViewState.UNREGISTERED
            }
        }
    }

    fun saveNickName(nickName: String) {

        viewModelScope.launch(Dispatchers.IO) {

            saveUserNameUseCase(nickName)

        }

    }

}

sealed class MainViewState{

    object UNREGISTERED : MainViewState()

    object REGISTERED : MainViewState()

    object LOADING : MainViewState()

}