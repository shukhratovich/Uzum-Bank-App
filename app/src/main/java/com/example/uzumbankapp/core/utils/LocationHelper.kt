package com.example.uzumbankapp.core.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.Locale

object LocationHelper {
    private lateinit var pref: SharedPreferences

    fun attach(context: Context): Context {
        pref = context.getSharedPreferences("My_pref", Context.MODE_PRIVATE)
        val savedLanguage = pref.getString("LANGUAGE", "uz") ?: "uz"
        return setLocation(context, savedLanguage)
    }

    fun setLocation(context: Context, lang: String): Context {
        pref.edit().putString("LANGUAGE", lang).apply()
        updateResource(context, lang)
        return context
    }

    private fun updateResource(context: Context, lang: String) {
        val locale = Locale(lang)
        val resource = context.resources

        val configuration = resource.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        context.createConfigurationContext(configuration)
        resource.updateConfiguration(configuration, resource.displayMetrics)
    }
}