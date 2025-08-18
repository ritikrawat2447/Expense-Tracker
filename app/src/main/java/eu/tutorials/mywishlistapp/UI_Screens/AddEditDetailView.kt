package eu.tutorials.mywishlistapp.UI_Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorials.mywishlistapp.Components.AppBarView
import eu.tutorials.mywishlistapp.Components.ExpenseTextField
import eu.tutorials.mywishlistapp.Components.validData
import eu.tutorials.mywishlistapp.R
import eu.tutorials.mywishlistapp.ViewModel.ExpenseViewModel
import eu.tutorials.mywishlistapp.data.Expense
import eu.tutorials.mywishlistapp.data.apiGateway
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: ExpenseViewModel,
    navController: NavController
) {
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if (id != 0L) {
        val expense = viewModel.getAExpenseById(id)
            .collectAsState(initial = Expense(0L, "", "", "", "", "", "", ""))
        viewModel.amountState = expense.value.amount
        viewModel.categoryState = expense.value.category
        viewModel.paymentMethodState = expense.value.payment_method
        viewModel.merchantNameState = expense.value.merchant_name
        viewModel.tagState = expense.value.tag
        viewModel.sourceState = expense.value.source
    } else {
        viewModel.amountState = ""
        viewModel.categoryState = ""
        viewModel.paymentMethodState = ""
        viewModel.merchantNameState = ""
        viewModel.tagState = ""
        viewModel.sourceState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title =
                    if (id != 0L) stringResource(id = R.string.update_expense)
                    else stringResource(id = R.string.add_expense)
            ) { navController.navigateUp() }
        },
        scaffoldState = scaffoldState
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ExpenseTextField(
                label = "Amount",
                value = viewModel.amountState,
                onValueChanged = {
                    viewModel.amountChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            ExpenseTextField(
                label = "Category",
                value = viewModel.categoryState,
                onValueChanged = {
                    viewModel.categoryChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            ExpenseTextField(
                label = "Payment Method",
                value = viewModel.paymentMethodState,
                onValueChanged = {
                    viewModel.paymentChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            ExpenseTextField(
                label = "Merchant Name",
                value = viewModel.merchantNameState,
                onValueChanged = {
                    viewModel.merchantChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            ExpenseTextField(
                label = "Tag",
                value = viewModel.tagState,
                onValueChanged = {
                    viewModel.tagChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            ExpenseTextField(
                label = "Source",
                value = viewModel.sourceState,
                onValueChanged = {
                    viewModel.sourceChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))


            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                if (validData(viewModel)) {
                    if (id != 0L) {
                        viewModel.updateExpense(
                            Expense(
                                id = id,
                                amount = viewModel.amountState.trim(),
                                category = viewModel.categoryState.trim(),
                                payment_method = viewModel.paymentMethodState.trim(),
                                merchant_name = viewModel.merchantNameState.trim(),
                                tag = viewModel.tagState.trim(),
                                source = viewModel.sourceState.trim()
                            )
                        )
                    } else {
                        viewModel.addExpense(
                            Expense(
                                amount = viewModel.amountState.trim(),
                                category = viewModel.categoryState.trim(),
                                payment_method = viewModel.paymentMethodState.trim(),
                                merchant_name = viewModel.merchantNameState.trim(),
                                tag = viewModel.tagState.trim(),
                                source = viewModel.sourceState.trim()
                            )
                        )
                        apiGateway()
                        snackMessage.value = "Expense has been created"
                    }
                } else {
                    //
                    snackMessage.value = "Enter fields to create a wish"
                }
                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_expense)
                    else stringResource(
                        id = R.string.add_expense
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

}
