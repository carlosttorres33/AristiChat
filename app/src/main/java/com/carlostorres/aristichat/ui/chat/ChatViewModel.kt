package com.carlostorres.aristichat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.aristichat.domain.GetMessagesUseCase
import com.carlostorres.aristichat.domain.GetUserNameUseCase
import com.carlostorres.aristichat.domain.SaveUserNameUseCase
import com.carlostorres.aristichat.domain.SendMessageUseCase
import com.carlostorres.aristichat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    var name = ""

    init {
        getUserName()
        getMessages()
    }

    private fun getUserName() {

        viewModelScope.launch(Dispatchers.IO){
            name = getUserNameUseCase()
        }

    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList:StateFlow<List<MessageModel>> = _messageList

    private fun getMessages(){

        viewModelScope.launch {

            getMessagesUseCase().collect(){
                Log.d("Aris tuto", "info: $it")
                _messageList.value = it

            }

        }

    }

    fun sendMessage(){

        val msg = "jelow"
        sendMessageUseCase(msg)

    }

}