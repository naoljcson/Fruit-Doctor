package com.example.fruitdoc.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.fruitdoc.util.Constants.CURRENT_LANGUAGE
import com.example.fruitdoc.util.Constants.LOCAL_STORAGE


class PreferenceHelper(context: Context) {
    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(LOCAL_STORAGE, MODE_PRIVATE)
    }

    fun defaultPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(LOCAL_STORAGE, MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    var language: String
        get() = preferences.getString(CURRENT_LANGUAGE, "am").toString()
        set(value) = preferences.edit().putString(CURRENT_LANGUAGE, value).apply()


    /**
     * puts a value for the given [key].
     */
    operator fun SharedPreferences.set(key: String, value: Any?) = when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     * finds a preference based on the given [key].
     * [T] is the type of value
     * @param defaultValue optional defaultValue - will take a default defaultValue if it is not specified
     */
    inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T = when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "") as T
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
        else -> throw UnsupportedOperationException("Not yet implemented")
    }


}