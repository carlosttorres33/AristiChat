package com.carlostorres.aristichat.domain

import com.carlostorres.aristichat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val firebaseChatService: FirebaseChatService
) {

    operator fun invoke() = firebaseChatService.getMessages()

}
