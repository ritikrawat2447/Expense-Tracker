package eu.tutorials.mywishlistapp.data

import android.content.Context
import androidx.room.Room

object Graph {

    lateinit var database: ExpenseDatabase

    val expenseRepository by lazy {
        ExpenseRepository(
            expenseDao = database.ExpenseDao()
        )
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, ExpenseDatabase::class.java, "expenses.db").build()
    }

}