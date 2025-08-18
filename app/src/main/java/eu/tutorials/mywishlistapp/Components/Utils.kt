package eu.tutorials.mywishlistapp.Components

import eu.tutorials.mywishlistapp.ViewModel.ExpenseViewModel

fun validData(viewModel: ExpenseViewModel): Boolean {
    if (viewModel.paymentMethodState.isEmpty()) return false
    if (viewModel.amountState.isEmpty()) return false
    if (viewModel.sourceState.isEmpty()) return false
    if (viewModel.tagState.isEmpty()) return false
    if (viewModel.merchantNameState.isEmpty()) return false
    if (viewModel.categoryState.isEmpty()) return false
    return true
}