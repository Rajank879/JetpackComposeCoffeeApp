package com.rajan.CoffeeShop.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

object OnboardingDataStore {
    private val KEY_SEEN_WELCOME = booleanPreferencesKey("has_seen_welcome")

    fun hasSeenWelcomeFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[KEY_SEEN_WELCOME] ?: false }

    suspend fun setHasSeenWelcome(context: Context, seen: Boolean) {
        context.dataStore.edit { prefs -> prefs[KEY_SEEN_WELCOME] = seen }
    }
}
