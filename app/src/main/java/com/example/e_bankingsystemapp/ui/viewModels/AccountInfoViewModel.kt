package com.example.e_bankingsystemapp.ui.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_bankingsystemapp.data.datasource.BankDataSource
import com.example.e_bankingsystemapp.data.model.BankItem
import com.example.e_bankingsystemapp.tempAppData
import kotlinx.coroutines.launch

class AccountInfoViewModel(
    private val dataSource: BankDataSource = tempAppData.sharedDataSource
): ViewModel() {

    private val _balance = mutableStateOf(0.0)
    val balance: State<Double> = _balance

    private var _transactions = mutableStateListOf<BankItem>()
    val transactions:List<BankItem> = _transactions

    init{
        loadData()
    }


    private fun loadData(){
        viewModelScope.launch {
            _balance.value = dataSource.getBalance()
            _transactions.clear()
            _transactions.addAll(dataSource.getTransactions())
        }
    }

    fun deleteTransaction(transactionId:String){
        viewModelScope.launch {
            dataSource.deleteTransaction(transactionId)
            loadData()
        }
    }
}
