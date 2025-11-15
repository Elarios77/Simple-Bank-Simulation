package com.example.e_bankingsystemapp.ui.bankScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.e_bankingsystemapp.R


@Composable
fun MainScreen(
    onDepositClicked: () -> Unit,
    onWithdrawalClicked: () -> Unit,
    onAccountInfoClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.atm2),
            contentDescription = stringResource( R.string.background),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Row(modifier = Modifier.fillMaxWidth().padding(start = 50.dp, end = 50.dp,top = 490.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    onDepositClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.deposit),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.deposit))
            }
            Button(
                onClick = {
                    onWithdrawalClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.withdrawal),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.withdrawal))
            }
        }
        Row(modifier = Modifier.fillMaxSize().padding(top = 650.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Button(
                onClick={
                    onAccountInfoClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_info_24),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.info))
            }
        }
    }
}
