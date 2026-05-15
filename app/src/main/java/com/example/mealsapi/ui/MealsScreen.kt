package com.example.mealsapi.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mealsapi.viewmodel.MealsViewModel

/**
 * Pantalla principal que muestra los detalles de una receta aleatoria.
 * 
 * @param viewModel ViewModel encargado de la lógica de negocio y estado de las recetas.
 */
@Composable
fun MealsScreen(viewModel: MealsViewModel) {
    val recipe by viewModel.recipeState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón para solicitar una nueva receta aleatoria
        Button(
            onClick = { viewModel.fetchRandomRecipe() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Cargando..." else "Generar Nueva Receta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Visualización de la receta si está disponible
        recipe?.let { meal ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = meal.strMeal,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Imagen de la receta mediante Coil
                AsyncImage(
                    model = meal.strMealThumb,
                    contentDescription = "Foto de ${meal.strMeal}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sección de Ingredientes
                Text("Ingredientes:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                meal.getIngredients().forEach { ingredient ->
                    Text(text = "• $ingredient")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sección de Preparación
                Text("Preparación:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                Text(text = meal.strInstructions)

                Spacer(modifier = Modifier.height(16.dp))

                // Sección de Video Tutorial
                if (!meal.strYoutube.isNullOrBlank()) {
                    Text("Video Tutorial:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))

                    YouTubePlayer(youtubeUrl = meal.strYoutube)

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

/**
 * Componente que muestra una miniatura clickeable del video de YouTube.
 * Al hacer clic, abre el video en la aplicación de YouTube o navegador.
 * 
 * @param youtubeUrl URL completa del video de YouTube.
 */
@Composable
fun YouTubePlayer(youtubeUrl: String) {
    val videoId = extractVideoId(youtubeUrl)
    val context = LocalContext.current

    if (!videoId.isNullOrEmpty()) {
        // Obtención de la miniatura oficial de YouTube mediante su ID
        val thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable {
                    // Intento de abrir la URL externa
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                    context.startActivity(intent)
                },
            contentAlignment = Alignment.Center
        ) {
            // Imagen de fondo (miniatura del video)
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = "Miniatura del video de YouTube",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Superposición semitransparente para contraste
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            // Icono central decorativo de Play
            Surface(
                shape = CircleShape,
                color = Color.Red.copy(alpha = 0.9f),
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Reproducir en YouTube",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

/**
 * Extrae el identificador de video de varios formatos de URL de YouTube.
 */
private fun extractVideoId(youtubeUrl: String): String? {
    return when {
        youtubeUrl.contains("v=") -> youtubeUrl.substringAfter("v=").substringBefore("&")
        youtubeUrl.contains("youtu.be/") -> youtubeUrl.substringAfter("youtu.be/").substringBefore("?")
        youtubeUrl.contains("embed/") -> youtubeUrl.substringAfter("embed/").substringBefore("?")
        else -> null
    }
}
