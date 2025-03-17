package com.synac.quiztime.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.synac.quiztime.data.util.Constant.CORRECT_ANSWERS_PREF_KEY
import com.synac.quiztime.data.util.Constant.QUESTIONS_ATTEMPTED_PREF_KEY
import com.synac.quiztime.data.util.Constant.USERNAME_PREF_KEY
import com.synac.quiztime.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val prefs: DataStore<Preferences>
): UserPreferencesRepository {

    companion object {
        private val QUESTIONS_ATTEMPTED_KEY = intPreferencesKey(QUESTIONS_ATTEMPTED_PREF_KEY)
        private val CORRECT_ANSWER_KEY = intPreferencesKey(CORRECT_ANSWERS_PREF_KEY)
        private val USERNAME_KEY = stringPreferencesKey(USERNAME_PREF_KEY)
    }

    override fun getQuestionsAttempted(): Flow<Int> {
        return prefs.data.map { preferences ->
            preferences[QUESTIONS_ATTEMPTED_KEY] ?: 0
        }
    }

    override fun getCorrectAnswers(): Flow<Int> {
        return prefs.data.map { preferences ->
            preferences[CORRECT_ANSWER_KEY] ?: 0
        }
    }

    override suspend fun saveScore(
        questionAttempted: Int,
        correctAnswers: Int
    ) {
        prefs.edit { preferences ->
            preferences[QUESTIONS_ATTEMPTED_KEY] = questionAttempted
            preferences[CORRECT_ANSWER_KEY] = correctAnswers
        }
    }

    override fun getUsername(): Flow<String> {
        return prefs.data.map { preferences ->
            preferences[USERNAME_KEY] ?: "Android Developer"
        }
    }

    override suspend fun saveUsername(name: String) {
        prefs.edit { preferences ->
            preferences[USERNAME_KEY] = name
        }
    }
}