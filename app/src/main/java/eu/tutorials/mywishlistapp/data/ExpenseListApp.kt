package eu.tutorials.mywishlistapp.data

import android.app.Application

class ExpenseListApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}