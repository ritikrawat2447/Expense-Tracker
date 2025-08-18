package eu.tutorials.mywishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAExpense(expenseEntity: Expense )

    @Query("Select * from `expense-table`")
    abstract fun getAllExpenses(): Flow<List<Expense>>

    @Update
    abstract suspend fun updateAExpense(expenseEntity: Expense)

    @Delete
    abstract suspend fun deleteAExpense(expenseEntity: Expense)

    @Query("Select * from `expense-table` where id=:id")
    abstract fun getAExpenseById(id:Long): Flow<Expense>


}