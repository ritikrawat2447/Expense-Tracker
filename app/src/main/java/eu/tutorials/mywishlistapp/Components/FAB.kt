package eu.tutorials.mywishlistapp.Components

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import eu.tutorials.mywishlistapp.Screen

@Composable
fun floatingActionButton(navController: NavController) {
    FloatingActionButton(
        modifier = Modifier.padding(all = 20.dp),
        contentColor = Color.White,
        onClick = {
//            Toast.makeText(context, "FAButton Clicked", Toast.LENGTH_LONG).show()
            navController.navigate(Screen.AddScreen.route + "/0L")

        }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}