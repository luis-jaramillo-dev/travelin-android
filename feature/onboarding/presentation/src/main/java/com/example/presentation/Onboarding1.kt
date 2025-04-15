package com.example.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
// import androidx.compose.foundation.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.projectlab.feature.onboarding.presentation.R

/**
 * 1) Componente que muestra la imagen de fondo.
 */
@Composable
fun Onboarding1BackgroundImage(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.onboarding1_background_jpg)
    Image(
        painter = image,
        contentDescription = "Onboarding background image",
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Preview (showBackground = true)
@Composable
fun BackgroundImagePreview() {
    Onboarding1BackgroundImage()
}


/**
 * 2) Componente que muestra el título y descripción.
 */
@Composable
fun Onboarding1TitleAndDescription(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
                .copy(fontSize = 30.sp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left //podria ser redundante?
            //textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
                .copy(fontSize = 15.sp),
            textAlign = TextAlign.Left //podria ser redundante?
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Onboarding1TitleAndDescriptionPreview() {
    Onboarding1TitleAndDescription(
        title = "Get ready for the next trip",
        description = "Find thousands of tourist destinations ready for you to visit"
    )
}


/**
 * 3) Componente para el botón “Next”.
 */
@Composable
fun Onboarding1Button(
    buttonText: String = "Next",
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        // definimos colores del botón:
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Implementar Material Design
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),

    ) {
        Text(text = buttonText)
    }
}

@Preview(showBackground = true)
@Composable
fun Onboarding1ButtonPreview() {
    Onboarding1Button()
}

/**
 * 4) Creamos un componente conteneror para el botón y el texto, blanco y vacio.
 */
@Composable
fun Onboarding1Container(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(30.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun Onboarding1ContainerPreview() {
    Onboarding1Container(){
        Text(text = "Hola")
    }
}

/**
 * 5) Componente que añade una imagen Logo .png
 */
@Composable
fun Onboarding1ImageLogo(
    modifier: Modifier = Modifier
) {
    val image = painterResource(id = R.drawable.image)
    Image(
        painter = image,
        contentDescription = "Onboarding image",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun Onboarding1ImageLogoPreview() {
    Onboarding1ImageLogo(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

/**
 * 6) Componente que muestra los puntos de la parte inferior de la pantalla.
 *    - Se usa para indicar el número de pantallas onboarding y cuál está activa.
 */
@Composable
fun Onboarding1CustomDotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.White,
    inactiveColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            // Cada punto es un Box circular. TODO: darle forma ovalada
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (index == selectedIndex) activeColor else inactiveColor)
                    .padding(4.dp)
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Onboarding1CustomDotsIndicatorPreview() {
    // Simulamos un carrusel con 3 pantallas y la primera activa
    Onboarding1CustomDotsIndicator(
        totalDots = 3,
        selectedIndex = 0,
        modifier = Modifier.padding(16.dp)
    )
}

/**
 * 7) Componente principal de esta pantalla (Onboarding1Screen).
 *    - Aquí unimos los elementos Composables que hemos creado.
 */
// TODO: Implementar estilos de Material Design adecuados al proyecto.
@Composable
fun Onboarding1Screen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Onboarding1BackgroundImage()

        //Imagen Logo
        Onboarding1ImageLogo(
            modifier = Modifier
                .height(200.dp)
                .padding(start = 20.dp, top = 20.dp, bottom = 70.dp)
                .align(Alignment.CenterStart)
        )

        // Dots Indicator.
        Onboarding1CustomDotsIndicator(
            totalDots = 3,
            selectedIndex = 0,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp)
        )

        // Onboarding1Container:
        Onboarding1Container(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 30.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(340.dp)

        ) {
            // Contenedor para Texto y Botón
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                // "Empujamos" el contenido al fondo usando Spacer con weight(1f)
                Spacer(modifier = Modifier.weight(1f))

                // Título y descripción
                Onboarding1TitleAndDescription(
                    title = "Get ready for the next trip",
                    description = "Find thousands of tourist destinations ready for you to visit"
                )

                Spacer(modifier = Modifier.height(100.dp))

                // Botón "Next"
                Onboarding1Button(
                    buttonText = "Next",
                    onClick = {
                    // TODO: Agregar lógica para navegar a la siguiente pantalla, cambiando
                    //  Onboarding1CustomDotsIndicator
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Onboarding1ScreenPreview() {
    Onboarding1Screen()
}



