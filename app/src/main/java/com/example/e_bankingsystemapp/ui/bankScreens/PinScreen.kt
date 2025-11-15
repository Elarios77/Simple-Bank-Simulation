package com.example.e_bankingsystemapp.ui.bankScreens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_bankingsystemapp.R


@Composable
fun PinScreen(
    navController: NavHostController
) {

    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.LightGray) {
            Column(modifier = Modifier.padding(20.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Gray),
                verticalArrangement = Arrangement.SpaceBetween)
            {
                Spacer(modifier = Modifier.weight(1f))
                Text(stringResource(R.string.enterPin),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(40.dp))
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    repeat(4){index ->
                        Text(
                            text = if(index < pin.length) "*" else "",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = if(index < pin.length) Color.Black else Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.width(280.dp).align(Alignment.CenterHorizontally)
                ) {
                    items(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, "", 0, "⌫")) { item ->
                        when (item) {
                            "⌫" -> {
                                IconButton(
                                    onClick = {
                                        if (pin.isNotEmpty()) pin = pin.dropLast(1)
                                    },
                                    modifier = Modifier.size(80.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Backspace,
                                        contentDescription = "Delete",
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }
                            "" -> {
                                Box(modifier = Modifier.size(80.dp))
                            }
                            else -> {
                                Button(
                                    onClick = {
                                        if (pin.length < 4) {
                                            pin += item.toString()
                                            if (pin.length == 4) {
                                                if (pin == "1234") {
                                                    navController.navigate(BankNavigation.Main.name)
                                                } else {
                                                    Toast.makeText(context, "Wrong PIN", Toast.LENGTH_SHORT).show()
                                                    pin = ""
                                                }
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = Color.Black
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(0.dp),
                                    modifier = Modifier.size(80.dp)
                                ) {
                                    Text(
                                        item.toString(),
                                        style = MaterialTheme.typography.headlineMedium
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(0.5f))
            }
            }

}

