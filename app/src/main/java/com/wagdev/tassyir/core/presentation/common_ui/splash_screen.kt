package com.wagdev.tassyir.core.presentation.common_ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wagdev.tassyir.R
import com.wagdev.tassyir.note_feature.presentation.util.Screen
import kotlinx.coroutines.delay

@Composable
fun Splash(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
               Color.White
            )
    ) {
        val scale = remember { Animatable(0f) }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = { OvershootInterpolator(4f).getInterpolation(it) }
                )
            )
            delay(2000L) // Simulating a 2-second delay
            navController.navigate(Screen.Home.route)
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_icon),
                contentDescription = "Notebook Icon",
                modifier= Modifier
                    .size(130.dp)
                    .scale(scale.value)
            )
            Text(
                text = "Daily Notes",
                modifier = Modifier.padding(top = 16.dp),
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Capture your thoughts, every day.",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.Black.copy(alpha = 0.8f),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
