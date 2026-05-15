package com.example.mealsapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mealsapi.ui.MealsScreen
import com.example.mealsapi.ui.theme.MealsApiTheme
import com.example.mealsapi.viewmodel.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Actividad principal de la aplicación.
 * Utiliza @AndroidEntryPoint para permitir la inyección de dependencias por Hilt.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealsApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Inyección del ViewModel mediante Hilt
                    val viewModel: MealsViewModel = hiltViewModel()

                    // Contenedor principal que respeta los márgenes del sistema (Edge-to-Edge)
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MealsScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
