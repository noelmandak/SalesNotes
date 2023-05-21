package com.example.salesnotes.data

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val PREF_NAME = "MyPreferences"
    private const val KEY_TOKEN = "token"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_TOKEN, null)
    }
}