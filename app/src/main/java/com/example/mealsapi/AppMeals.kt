package com.example.mealsapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase de aplicación principal que inicializa Dagger Hilt para la inyección de dependencias.
 */
@HiltAndroidApp
class AppMeals : Application()
