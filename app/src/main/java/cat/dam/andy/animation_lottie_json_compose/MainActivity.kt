package cat.dam.andy.animation_lottie_json_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cat.dam.andy.animation_lottie_json_compose.ui.theme.Animation_lottie_json_composeTheme
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.*
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Animation_lottie_json_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Animations()
                }
            }
        }
    }
}

@Composable
fun Animations() {
    // Opció 1: Utilitzant un fitxer Raw d'animació Lottie
    val rawComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ok))

    // Opció 2: Utilitzant una URL d'animació Lottie
    val urlComposition by rememberLottieComposition(spec = LottieCompositionSpec.Url("your_animation_url_here"))
    val progress by animateLottieCompositionAsState(composition = rawComposition) // Use 'urlComposition' for Way 2

    val animationList = listOf(
        R.raw.ok,
        R.raw.foodcarousel,
        R.raw.orderfood,
        R.raw.whatdoieat
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(animationList.size) { index ->
            AnimationItem(animationList[index])
        }
    }
}

@Composable
fun AnimationItem(animationResId: Int) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            animationResId
        )
    )
    val progress by animateLottieCompositionAsState(composition = composition)
    LottieAnimation(
        composition = composition,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f),
        iterations = LottieConstants.IterateForever,
        contentScale = ContentScale.FillBounds,
    )
}