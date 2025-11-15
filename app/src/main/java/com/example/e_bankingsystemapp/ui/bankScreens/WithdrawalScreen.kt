package com.example.e_bankingsystemapp.ui.bankScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_bankingsystemapp.R
import com.example.e_bankingsystemapp.ui.viewModels.WithdrawalViewModel


@Composable
fun WithdrawalScreen(
    viewModel: WithdrawalViewModel = viewModel(),
    onConfirmClicked: () -> Unit)
{
    val context = LocalContext.current
    val amount = viewModel.amount

    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.LightGray) {
        Column(modifier = Modifier.padding(top = 200.dp, bottom = 200.dp, start = 25.dp, end = 25.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = stringResource(R.string.withdrawalAmount))
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = amount,
                    label = {Text(text = stringResource(R.string.enterAmount))},
                    onValueChange = { newValue ->
                        if(newValue.length <=8){
                            viewModel.onAmountChange(newValue)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = {
                    viewModel.withdraw(
                        context = context,
                        onSuccess = {
                            onConfirmClicked()
                        }
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.confirm))
            }
        }
    }

}
