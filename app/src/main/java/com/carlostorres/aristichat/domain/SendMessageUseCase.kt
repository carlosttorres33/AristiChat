package com.carlostorres.aristichat.domain

import com.carlostorres.aristichat.data.network.FirebaseChatService
import com.carlostorres.aristichat.data.network.dto.MessageDto
import com.carlostorres.aristichat.data.network.dto.UserDto
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val firebaseChatService: FirebaseChatService
) {

    operator fun invoke(msg : String, nickName: String){

        val calendar = Calendar.getInstance()

        val currentMsg = msg

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val userDto = UserDto(
            userName = nickName,
            admin = false
        )

        val messageDto = MessageDto(
            msg = currentMsg,
            hour = "$hour:$min ",
            date = "$day/$month/$year",
            user = userDto
        )

        firebaseChatService.sendMessageToFirebase(messageDto)

    }

}