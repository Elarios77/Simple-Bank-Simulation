package com.example.e_bankingsystemapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.e_bankingsystemapp.ui.bankScreens.BankApp
import com.example.e_bankingsystemapp.ui.theme.EbankingSystemAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EbankingSystemAppTheme {
                BankApp()
            }
        }
    }
}

