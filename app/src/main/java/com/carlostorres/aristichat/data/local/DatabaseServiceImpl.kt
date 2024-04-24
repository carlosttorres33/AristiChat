package com.carlostorres.aristichat.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.carlostorres.aristichat.domain.DatabaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseServiceImpl @Inject constructor(
    private val context: Context
): DatabaseService {

    companion object{

        private val USER_NAME = stringPreferencesKey("username")

    }


    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user"
    )

    override suspend fun saveUserName(nickName: String) {

        context.userPreferencesDataStore.edit { preferences ->

            preferences[USER_NAME] = nickName

        }

    }

    override fun getUserName() : Flow<String> =
        context.userPreferencesDataStore.data.map { preferences ->
            preferences[USER_NAME] ?: ""
        }

}