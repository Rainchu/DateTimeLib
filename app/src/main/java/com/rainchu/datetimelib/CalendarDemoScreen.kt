package com.rainchu.datetimelib

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDemoScreen() {

    var showBottomSheet by remember { mutableStateOf(false) }

    val selectedDate = remember {
        mutableStateOf("")
    }

    val sheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(

        ) {
            Button(
                onClick = {
                    showBottomSheet = true
                }
            ) {


                Text("Open Calendar")

            }

            Text(text = selectedDate.value)
        }

        if (showBottomSheet) {

            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    showBottomSheet = false
                }
            ) {

                RainchuCalenderView(

                    modifier = Modifier.fillMaxWidth(),

                    onOk = { date ->
                       if (date != null) {
                           selectedDate.value = date
                       }
                        println(date)

                        showBottomSheet = false

                    },

                    onCancel = {

                        showBottomSheet = false

                    }
                )
            }
        }
    }
}