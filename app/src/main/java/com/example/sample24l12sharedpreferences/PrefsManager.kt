package com.example.sample24l12sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefsManager(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var switchIsChecked: Boolean
        get() = prefs.getBoolean(KEY_SWITCH, false)
        set(value) {
            prefs.edit {
                putBoolean(KEY_SWITCH, value)
            }
        }

    var editTextValue by EditTextPrefsDelegate(prefs, KEY_EDIT_TEXT)

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val KEY_SWITCH = "key_switch"
        private const val KEY_EDIT_TEXT = "key_edit_text"
    }
}

class EditTextPrefsDelegate(
    private val prefs: SharedPreferences,
    private val key: String
) : ReadWriteProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return prefs.getString(key, "") ?: ""
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        prefs.edit {
            putString(key, value)
        }
    }
}