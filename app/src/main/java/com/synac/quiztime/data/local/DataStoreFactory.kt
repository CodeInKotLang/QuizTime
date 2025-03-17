package com.synac.quiztime.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.synac.quiztime.data.util.Constant.DATA_STORE_FILE_NAME

object DataStoreFactory {

    fun create(context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(name = DATA_STORE_FILE_NAME)
        }
    }

}