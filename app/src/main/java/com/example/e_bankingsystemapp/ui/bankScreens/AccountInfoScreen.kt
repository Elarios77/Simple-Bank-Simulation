package com.example.e_bankingsystemapp.ui.bankScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.e_bankingsystemapp.R
import com.example.e_bankingsystemapp.data.model.BankItem
import com.example.e_bankingsystemapp.ui.viewModels.AccountInfoViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AccountInfoScreen(
    navController: NavHostController
) {

    val viewModel: AccountInfoViewModel = viewModel()
    val balance by viewModel.balance
    val transactions = viewModel.transactions

    var balanceExpanded by remember { mutableStateOf(false) }
    var transExpanded by remember { mutableStateOf(false) }

    AppBar(
        navigateBack = {
            navController.popBackStack()
        }
    ) { innerPadding ->

        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            color = Color.LightGray) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.Gray)
            ) {
                item {
                    ExpandedBalanceCard(
                        balance = balance,
                        isExpanded = balanceExpanded,
                        onExpandChange = {balanceExpanded = it}
                    )
                }
                item {
                    ExpandedTransactionCard(
                        transactions = transactions,
                        isExpanded = transExpanded,
                        onExpandChange = {transExpanded = it},
                        onDeleteTransaction = {transactionId ->
                            viewModel.deleteTransaction(transactionId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ExpandedBalanceCard(
    balance:Double,
    isExpanded:Boolean,
    onExpandChange:(Boolean) -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp))
    {
        Column(modifier = Modifier.padding(16.dp)){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.currentBalance),
                    style = MaterialTheme.typography.headlineSmall)
                IconButton(onClick = {onExpandChange(!isExpanded)})
                {
                    Icon(
                        imageVector = if(isExpanded)
                            Icons.Default.ExpandLess
                        else
                            Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }
            }

            if(isExpanded){
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "$balance €",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Last Update: ${getCurrentDate()}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

private fun getCurrentDate():String{
    return SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault()).format(Date())
}

@Composable
private fun ExpandedTransactionCard(
    transactions: List<BankItem>,
    isExpanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onDeleteTransaction: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.transactionHistory),
                    style = MaterialTheme.typography.headlineSmall
                )
                IconButton(onClick = { onExpandChange(!isExpanded) }) {
                    Icon(
                        imageVector = if (isExpanded)
                            Icons.Default.ExpandLess
                        else
                            Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }
            }

            // Expandable Content
            if (isExpanded) {
                Spacer(modifier = Modifier.height(12.dp))

                if (transactions.isEmpty()) {
                    Text(
                        stringResource(R.string.noTransactions),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp)
                    ) {
                        items(transactions){transaction ->
                            TransactionItem(
                                transaction = transaction,
                                onDelete = {
                                    onDeleteTransaction(transaction.id)
                                }
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TransactionItem(transaction: BankItem,
                    onDelete:()-> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = transaction.type,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "€${transaction.amount}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.type == "DEPOSIT") Color.Green else Color.Red
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
        navigateBack: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(R.string.info))
                    },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.LightGray
                    )
                )
            },
            content = content
        )
}
