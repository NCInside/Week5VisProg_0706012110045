package com.example.week5visprog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.week5visprog.viewmodel.MovieDetailViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun MovieDetailScreen(
    id: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original${state.movie?.backdrop_path}")
    if(!state.error) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .height(233.dp)
                    ) {
                        if (painter.state is AsyncImagePainter.State.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 24.dp)
                        ) {
                            state.movie?.let {
                                Text(
                                    text = it.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp,
                                    modifier = Modifier.width(192.dp)
                                )
                            }
                        }
                    }
                }
            }
            Text(
                text = "Genre: ",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                state.movie?.genres?.size?.let {
                    items(it) { i ->
                        val genre = state.movie.genres[i]
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.padding(4.dp),
                            backgroundColor = Color(0xFFe4c775)
                        ) {
                            Text(
                                text = genre.name,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
            Text(
                text = "Spoken Languages: ",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                state.movie?.spoken_languages?.size?.let {
                    items(it) { i ->
                        val language = state.movie.spoken_languages[i]
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.padding(4.dp),
                            backgroundColor = Color(0xFFe4c775)
                        ) {
                            Text(
                                text = language.name,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
            Text(
                text = "Production Companies: ",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                state.movie?.production_companies?.size?.let {
                    items(it) { i ->
                        val company = state.movie.production_companies[i]
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .padding(6.dp)
                        ) {
                            Box(
                                modifier = Modifier.size(64.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original${company.logo_path}"),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                    }
                }
            }
            Text(
                text = "Overview: ",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 24.dp)
            )
            state.movie?.overview?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 24.dp)
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        }
        else if(state.error) {
            Text(
                text = "Error: Get Data Failed",
                color = MaterialTheme.colors.error
            )
        }
    }
}