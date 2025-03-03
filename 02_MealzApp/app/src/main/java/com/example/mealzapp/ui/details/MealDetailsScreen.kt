package com.example.mealzapp.ui.details

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil3.compose.AsyncImage
import com.example.mealzapp.model.response.MealResponse
import kotlin.math.min

@Composable
fun MealDetailsScreen(meal: MealResponse){
    val scrollState = rememberLazyListState()

    val offset = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex))
    val imageSize by animateDpAsState(max(100.dp, 200.dp * offset))

    Surface(color = MaterialTheme.colorScheme.background) {
        Column (
            Modifier.padding(top = 32.dp)
        ) {
            Surface(
                shadowElevation = 4.dp
            ) {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Card (
                        Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        )
                    ) {
                        AsyncImage(
                            model = meal.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(imageSize)
                                .clip(CircleShape),
                            onError = {
                                Log.e("AsyncImage", "Image load failed: ${it.result.throwable}")
                            }
                        )
                    }
                    Text(
                        meal.name,
                        Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyContentList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState) {
                items(dummyContentList){ dummyItem ->
                    Text("Text number $dummyItem", Modifier.padding(24.dp))
                }
            }
        }
    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp){
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}