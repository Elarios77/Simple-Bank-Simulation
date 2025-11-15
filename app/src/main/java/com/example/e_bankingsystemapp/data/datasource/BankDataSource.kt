package com.example.e_bankingsystemapp.data.datasource

import com.example.e_bankingsystemapp.data.model.BankItem

interface BankDataSource {

    suspend fun getBalance(): Double
    suspend fun deposit(amount: Double)
    suspend fun withdraw(amount: Double): Boolean
    suspend fun getTransactions():List<BankItem>
    suspend fun addTransaction(transaction: BankItem)
    suspend fun deleteTransaction(transactionId: String)
}