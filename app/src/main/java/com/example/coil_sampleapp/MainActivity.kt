package com.example.coil_sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.coil_sampleapp.ui.theme.CoilSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoilSampleAppTheme {
                Column {
                    asyncImage()
                    subComposeAsyncImage()
                    subComposeAsyncImage2()
                }
            }
        }
    }
}

@Composable
fun asyncImage() {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://picsum.photos/id/237/200")
            .crossfade(true)
            .crossfade(1000)
            .build(),
        placeholder = painterResource(id = R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
    )
}

@Composable
fun subComposeAsyncImage() {
    SubcomposeAsyncImage(
        model = "https://picsum.photos/id/237/200",
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = null
    )
}

@Composable
fun subComposeAsyncImage2() {
    SubcomposeAsyncImage(
        model = "https://picsum.photos/id/237/200",
        contentDescription = null
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            CircularProgressIndicator()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}
