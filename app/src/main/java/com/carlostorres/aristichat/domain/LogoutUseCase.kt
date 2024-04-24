package com.carlostorres.aristichat.domain

import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val databaseService: DatabaseService
) {

    suspend operator fun invoke(){

        databaseService.clear()

    }

}