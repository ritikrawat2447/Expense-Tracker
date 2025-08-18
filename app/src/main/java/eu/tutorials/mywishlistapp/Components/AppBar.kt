package eu.tutorials.mywishlistapp.Components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.mywishlistapp.R

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    val navigationIcon: (@Composable () -> Unit)? =
        if (title.contains("Expenses")) {
            null
        } else {
            {
                IconButton(onClick = { onBackNavClicked() }, modifier = Modifier.padding(start=6.dp,top=16.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 16.dp , top = 16.dp)
                    .heightIn(max = 24.dp)
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
        navigationIcon = navigationIcon
    )
}