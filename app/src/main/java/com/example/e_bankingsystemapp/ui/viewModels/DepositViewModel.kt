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

class DepositViewModel(
    private val dataSource: BankDataSource = tempAppData.sharedDataSource
): ViewModel() {

    private var _balance = mutableStateOf(0.0)

    var amount by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    init{
        loadBalance()
    }

    private fun loadBalance(){
        viewModelScope.launch {
            _balance.value = dataSource.getBalance()
        }
    }
    fun amountChange(newAmount: String){
        amount = newAmount
    }

    fun depositAmount(context: Context): Boolean{
        val deposit = amount.toDoubleOrNull()
        if(deposit !=null && deposit>0){
            isLoading = true
            viewModelScope.launch {
                try{
                    dataSource.deposit(deposit)
                    _balance.value = dataSource.getBalance()
                    Toast.makeText(
                        context,
                        "Successful Deposit",
                        Toast.LENGTH_SHORT
                    ).show()
                    amount = ""
                }catch (e: Exception){
                    Toast.makeText(
                        context,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }finally {
                    isLoading = false
                }
            }
            return true
        }else{
            Toast.makeText(
                context,
                "Enter valid amount",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }
}