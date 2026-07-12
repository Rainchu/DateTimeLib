package com.rainchu.datetimelib

import android.os.Build
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.rainchu.mylibrary.view.RainchuCalenderView
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RainchuCalenderView(
    modifier: Modifier = Modifier,
    onOk: (String?) -> Unit = {},
    onCancel: () -> Unit = {}
) {

    AndroidView(
        modifier = modifier,
        factory = { context ->

            RainchuCalenderView(context).apply {
                setMaxDate(LocalDate.now())

               // Log.d("TAG", "RainchuCalenderView: ${LocalDate.now()}")

                setOnOkClickListener { day ->
                    onOk(day?.date?.toString())
                }

                setOnCancelClickListener {
                    onCancel()
                }
            }
        }
    )
}