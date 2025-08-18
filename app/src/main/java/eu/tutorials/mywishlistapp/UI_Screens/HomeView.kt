package eu.tutorials.mywishlistapp.UI_Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.navigation.NavController
import eu.tutorials.mywishlistapp.Components.AppBarView
import eu.tutorials.mywishlistapp.Components.floatingActionButton
import eu.tutorials.mywishlistapp.Components.swipeToDismiss
import eu.tutorials.mywishlistapp.Screen
import eu.tutorials.mywishlistapp.ViewModel.ExpenseViewModel
import eu.tutorials.mywishlistapp.data.Expense

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: ExpenseViewModel
) {
//    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppBarView(title = "Expenses") },
        floatingActionButton = {
            floatingActionButton(navController)
        }

    ) {
        val wishlist = viewModel.getAllTransactions.collectAsState(initial = listOf())
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(wishlist.value, key = { expense -> expense.id }) { expense ->
                    // UI component to delete item with a swipe
                    swipeToDismiss(
                        viewModel, expense,
                        onEdit = { expense ->
                            val id = expense.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        },
                        onDelete = { expense ->
                            viewModel.deleteExpense(expense) // your delete logic
                        }
                    )
                }
            }
        }
    }

}


@Composable
fun ExpenseItem(expense: Expense, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = 10.dp,
        backgroundColor = Color.White,
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = expense.amount, fontWeight = FontWeight.ExtraBold)
            Text(text = expense.category)
        }
    }
}