package com.example.e_bankingsystemapp.data.model

import java.util.UUID

data class BankItem(
    val id: String = UUID.randomUUID().toString(),
    val type:String,
    val amount:Double,
    val date: String
)