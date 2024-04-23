package com.carlostorres.aristichat.data.network

import com.carlostorres.aristichat.data.network.dto.MessageDto
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseChatService @Inject constructor(
    private val reference: DatabaseReference
) {

    companion object{
        private const val PATH = "message"
    }

    fun sendMessageToFirebase(messageDto: MessageDto){

        val newMsg = reference.child(PATH).push()
        newMsg.setValue(messageDto)

    }

}