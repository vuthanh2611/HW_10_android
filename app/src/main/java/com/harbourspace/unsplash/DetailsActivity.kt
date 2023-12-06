package com.harbourspace.unsplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.harbourspace.unsplash.data.model.UnsplashItem
import com.harbourspace.unsplash.ui.theme.UnsplashTheme
import com.harbourspace.unsplash.utils.EXTRA_IMAGE



class DetailsActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val url = if (intent.hasExtra("extra.image")) {
      intent.extras?.get("extra.image") as UnsplashItem
    } else {
      null
    }

    setContent {
      UnsplashTheme {

        LazyColumn {
          item {
            val painter = rememberAsyncImagePainter(
              model = ImageRequest.Builder(LocalContext.current)
                .data(url?.urls?.regular)
                .build()
            )

            Image(
              modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
              painter = painter,
              contentScale = ContentScale.Crop,
              contentDescription = stringResource(id = R.string.description_image_preview)
            )
          }

          item {
            Row(
              modifier = Modifier.padding(16.dp)
            ) {
              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Instagram Username",
                  subtitle = url?.user?.instagram_username ?: ""

                )
              }

              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Created at",
                  subtitle = url?.created_at ?: ""
                )
              }
            }
          }

          item {
            Row(
              modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = stringResource(id = R.string.details_focal_title),
                  subtitle = stringResource(id = R.string.details_focal_default)

                )
              }

              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = stringResource(id = R.string.details_shutter_title),
                  subtitle = stringResource(id = R.string.details_shutter_default)
                )
              }
            }
          }

          item {
            Row(
              modifier = Modifier.padding(16.dp)
            ) {
              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Color",
                  subtitle = url?.color ?: ""
                )
              }

              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = stringResource(id = R.string.details_dimensions_title),
                  subtitle = stringResource(id = R.string.details_dimensions_default)
                )
              }
            }
          }

          item {
            Divider(
              modifier = Modifier.padding(start = 16.dp, end = 16.dp),
              thickness = 1.dp,
              color = Color.LightGray
            )
          }

          item {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
              horizontalArrangement = Arrangement.SpaceEvenly
            ) {

              Column(
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                AddImageInformation(
                  title = stringResource(id = R.string.details_views_title),
                  subtitle = stringResource(id = R.string.details_views_default)
                )
              }

              Column(
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                AddImageInformation(
                  title = stringResource(id = R.string.details_downloads_title),
                  subtitle = stringResource(id = R.string.details_downloads_default)
                )
              }

              Column(
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                AddImageInformation(
                  title = stringResource(id = R.string.details_likes_title),
                  subtitle = (url?.likes ?: "").toString()
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun AddImageInformation(
  title: String,
  subtitle: String
) {

  Text(
    text = title,
    fontSize = 17.sp,
    fontWeight = FontWeight.Bold
  )

  Text(
    text = subtitle,
    fontSize = 15.sp
  )
}