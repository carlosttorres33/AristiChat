package com.carlostorres.aristichat.ui.chat

import androidx.lifecycle.ViewModel
import com.carlostorres.aristichat.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    fun sendMessage(){

        val msg = "jelow"
        sendMessageUseCase(msg)

    }

}