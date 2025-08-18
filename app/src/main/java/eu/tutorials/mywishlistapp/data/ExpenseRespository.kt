package eu.tutorials.mywishlistapp.data

import kotlinx.coroutines.flow.Flow


class ExpenseRepository(private val expenseDao: ExpenseDao) {

    suspend fun addAExpense(expense: Expense){
        expenseDao.addAExpense(expense)
    }

    fun getExpenses(): Flow<List<Expense>> = expenseDao.getAllExpenses()

    fun getAExpenseById(id:Long) :Flow<Expense> {
        return expenseDao.getAExpenseById(id)
    }

    suspend fun updateAExpense(expense:Expense){
        expenseDao.updateAExpense(expense)
    }

    suspend fun deleteAExpense(expense: Expense){
        expenseDao.deleteAExpense(expense)
    }
}