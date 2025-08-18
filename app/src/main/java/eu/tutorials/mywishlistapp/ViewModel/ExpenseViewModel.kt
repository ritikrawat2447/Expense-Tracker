package eu.tutorials.mywishlistapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.mywishlistapp.data.Expense
import eu.tutorials.mywishlistapp.data.Graph
import eu.tutorials.mywishlistapp.data.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val expenseRepository: ExpenseRepository = Graph.expenseRepository
): ViewModel() {

    var amountState by mutableStateOf("")
    var paymentMethodState by mutableStateOf("")
    var tagState by mutableStateOf("")
    var categoryState by mutableStateOf("")
    var merchantNameState by mutableStateOf("")
    var sourceState by mutableStateOf("")


    fun amountChange(newString:String){
        amountState = newString
    }

    fun paymentChange(newString : String ){
        paymentMethodState = newString
    }

    fun tagChange(newString : String ){
        tagState = newString
    }

    fun categoryChange(newString : String ){
        categoryState = newString
    }

    fun merchantChange(newString : String ){
        merchantNameState = newString
    }

    fun sourceChange(newString : String ){
        sourceState = newString
    }

    lateinit var getAllTransactions: Flow<List<Expense>>

    init {
        viewModelScope.launch {
            getAllTransactions = expenseRepository.getExpenses()
        }
    }

    fun addExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.addAExpense(expense= expense)
        }
    }

    fun getAExpenseById(id:Long): Flow<Expense> {
        return expenseRepository.getAExpenseById(id)
    }

    fun updateExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.updateAExpense(expense= expense)
        }
    }

    fun deleteExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteAExpense(expense = expense)
            getAllTransactions = expenseRepository.getExpenses()
        }
    }
}