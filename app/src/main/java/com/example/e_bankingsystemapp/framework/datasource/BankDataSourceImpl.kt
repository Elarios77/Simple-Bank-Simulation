package com.example.e_bankingsystemapp.framework.datasource

import com.example.e_bankingsystemapp.data.datasource.BankDataSource
import com.example.e_bankingsystemapp.data.model.BankItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BankDataSourceImpl : BankDataSource  {

    private var currentBalance = 0.0
    private val transactions = mutableListOf<BankItem>()

    override suspend fun getBalance(): Double {
        return currentBalance
    }

    override suspend fun deposit(amount: Double) {
        if(amount > 10000){
            throw IllegalArgumentException("max. deposit amount: 10000€")
        }
        currentBalance += amount
        addTransaction(BankItem(type = "DEPOSIT", amount = amount, date = getCurrentDate()))
    }

    override suspend fun withdraw(amount: Double): Boolean {
        return if(amount <= currentBalance){
            currentBalance -=amount
            addTransaction(BankItem(type = "WITHDRAWAL",amount = amount, date = getCurrentDate()))
            true
        }else{
            false
        }
    }

    override suspend fun getTransactions(): List<BankItem> {
        return transactions.reversed()
    }

    override suspend fun addTransaction(transaction: BankItem) {
        transactions.add(transaction)
    }

    override suspend fun deleteTransaction(transactionId: String) {
        val transactionToRemove = transactions.find {it.id == transactionId}
        transactions.remove(transactionToRemove)
    }

    private fun getCurrentDate():String{
        return SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault()).format(Date())
    }
}