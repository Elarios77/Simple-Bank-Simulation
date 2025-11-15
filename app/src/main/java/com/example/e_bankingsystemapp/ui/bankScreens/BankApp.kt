package com.example.e_bankingsystemapp.ui.bankScreens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_bankingsystemapp.R

enum class BankNavigation(@StringRes val title: Int){

    Pin(title = R.string.pinName),
    Main(title = R.string.app_name),
    Deposit(title = R.string.newDeposit),
    Withdrawal(title= R.string.newWithdrawal),
    Info(title = R.string.accountInfo)
}


@Composable
fun BankApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BankNavigation.Pin.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = BankNavigation.Pin.name){
                PinScreen(navController = navController)
            }
            composable(route = BankNavigation.Main.name){
                MainScreen(
                    onDepositClicked = {
                        navController.navigate(BankNavigation.Deposit.name)
                    },
                    onWithdrawalClicked = {
                        navController.navigate(BankNavigation.Withdrawal.name)
                    },
                    onAccountInfoClicked = {
                        navController.navigate(BankNavigation.Info.name)
                    }
                )
            }
            composable(route = BankNavigation.Deposit.name) {
                DepositScreen(
                    onConfirmClicked= {
                        navController.popBackStack()
                    }
                )
            }
            composable(route = BankNavigation.Withdrawal.name){
                WithdrawalScreen(
                    onConfirmClicked = {
                        navController.popBackStack()
                    }
                )
            }
            composable(route = BankNavigation.Info.name) {
                AccountInfoScreen(
                    navController = navController
                )
            }
        }
    }
}