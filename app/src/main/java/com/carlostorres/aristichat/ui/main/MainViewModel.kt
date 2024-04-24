package com.carlostorres.aristichat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.aristichat.domain.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase
): ViewModel() {
    fun saveNickName(nickName: String) {

        viewModelScope.launch(Dispatchers.IO) {

            saveUserNameUseCase(nickName)

        }

    }


}