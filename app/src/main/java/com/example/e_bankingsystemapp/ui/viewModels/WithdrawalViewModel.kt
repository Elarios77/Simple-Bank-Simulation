package com.example.e_bankingsystemapp.ui.viewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_bankingsystemapp.data.datasource.BankDataSource
import com.example.e_bankingsystemapp.tempAppData
import kotlinx.coroutines.launch

class WithdrawalViewModel(
    private val dataSource: BankDataSource = tempAppData.sharedDataSource
): ViewModel() {

    private var _balance = mutableStateOf(0.0)

    var amount by mutableStateOf("")
        private set

    init {
        loadBalance()
    }

    private fun loadBalance() {
        viewModelScope.launch {
            _balance.value = dataSource.getBalance()
        }
    }

    fun onAmountChange(newAmount: String) {
        amount = newAmount
    }

    fun withdraw(context: Context, onSuccess:()-> Unit): Boolean {
        val withdrawAmount = amount.toDoubleOrNull()

        if (withdrawAmount == null || withdrawAmount <= 0 || withdrawAmount > 1000) {
            Toast.makeText(context, "Invalid amount", Toast.LENGTH_SHORT).show()
            return false
        }

        viewModelScope.launch {
            try {
                val success = dataSource.withdraw(withdrawAmount)
                if (success) {
                    _balance.value = dataSource.getBalance()
                    amount = ""
                    Toast.makeText(context, "Withdrawal successful", Toast.LENGTH_SHORT).show()
                    onSuccess()
                } else {
                    Toast.makeText(context, "Insufficient funds", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
}