package com.carlostorres.aristichat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.aristichat.domain.GetMessagesUseCase
import com.carlostorres.aristichat.domain.SendMessageUseCase
import com.carlostorres.aristichat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase
) : ViewModel() {

    init {
        getMessages()
    }

    var messageList = MutableStateFlow<List<MessageModel>>(emptyList())

    private fun getMessages(){

        viewModelScope.launch {

            getMessagesUseCase().collect(){
                Log.d("Aris tuto", "info: $it")
                messageList.value = it

            }

        }

    }

    fun sendMessage(){

        val msg = "jelow"
        sendMessageUseCase(msg)

    }

}