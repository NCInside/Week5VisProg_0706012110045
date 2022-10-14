package com.example.week5visprog.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week5visprog.model.Result

@Composable
fun MovieItem(
    movie: Result,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.Black),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "(${movie.popularity})",
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.release_date,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}