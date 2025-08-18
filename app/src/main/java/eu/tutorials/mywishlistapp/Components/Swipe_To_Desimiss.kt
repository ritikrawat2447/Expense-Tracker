package eu.tutorials.mywishlistapp.Components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tutorials.mywishlistapp.UI_Screens.ExpenseItem
import eu.tutorials.mywishlistapp.ViewModel.ExpenseViewModel
import eu.tutorials.mywishlistapp.data.Expense

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun swipeToDismiss(
    viewModel: ExpenseViewModel,
    expense: Expense,
    onEdit: (Expense) -> Unit,
    onDelete: (Expense) -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                onDelete
            }
            true
        }
    )
    SwipeToDismiss(
        state = dismissState,
        background = {
            val color by animateColorAsState(
                if (dismissState.dismissDirection
                    == DismissDirection.EndToStart
                ) Color.Red else Color.Transparent, label = ""
            )
            val alignment = Alignment.CenterEnd
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Icon",
                    tint = Color.White
                )
            }

        },
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(1f) },
        dismissContent = {
            ExpenseItem(expense = expense) {
                onEdit(expense)
            }
        }
    )
}