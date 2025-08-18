package eu.tutorials.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="expense-table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="timestamp")
    val timestamp: String="",
    @ColumnInfo(name="amount")
    val amount: String="",
    @ColumnInfo(name="category")
    val category: String="",
    @ColumnInfo(name="payment-method")
    val payment_method: String="",
    @ColumnInfo(name="merchant-name")
    val merchant_name: String="",
    @ColumnInfo(name="tag")
    val tag: String="",
    @ColumnInfo(name="source")
    val source: String=""
)