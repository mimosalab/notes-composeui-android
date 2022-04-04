package me.aravi.notes.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.aravi.notes.R
import me.aravi.notes.utils.NoteOrder
import me.aravi.notes.utils.OrderType
import java.util.*

@Composable
fun OrderSection(
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    var isOrderListDropDownExpanded by remember { mutableStateOf(false) }
    var isAscendingListDropDownExpanded by remember { mutableStateOf(false) }
    var orderListVal by remember { mutableStateOf("Date") }
    var ascendingListVal by remember { mutableStateOf("Descending") }
    val orderList = listOf("Title", "Date", "Color")
    val ascendingList = listOf("Ascending", "Descending")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp)
    ) {
        Row(
            modifier = Modifier.wrapContentWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = orderListVal,
                        onValueChange = { },
                        placeholder = { Text(text = "Date", color = Color.LightGray) },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isOrderListDropDownExpanded = true
                            }
                            .fillMaxWidth(0.9f)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(2.dp)),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.google_sans_medium))
                        ),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                "",
                                tint = Color.LightGray
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = isOrderListDropDownExpanded,
                        onDismissRequest = { isOrderListDropDownExpanded = false },
                        modifier = Modifier
                            .width(155.dp)
                            .background(Color.White)
                    ) {
                        orderList.forEach {
                            DropdownMenuItem(onClick = {
                                orderListVal = it
                                onOrderChange(
                                    when (it.lowercase()) {
                                        "title" -> NoteOrder.Title(noteOrder.orderType)
                                        "date" -> NoteOrder.Date(noteOrder.orderType)
                                        else -> NoteOrder.Color(noteOrder.orderType)
                                    }
                                )
                                isOrderListDropDownExpanded = false
                            }, modifier = Modifier.fillMaxWidth(0.8f)) {
                                Text(
                                    it,
                                    fontFamily = FontFamily(Font(R.font.google_sans_medium)),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = ascendingListVal,
                        onValueChange = { },
                        placeholder = { Text(text = "Ascending", color = Color.LightGray) },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isAscendingListDropDownExpanded = true
                            }
                            .fillMaxWidth(0.9f)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(2.dp)),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.google_sans_medium))
                        ),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                "",
                                tint = Color.LightGray
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = isAscendingListDropDownExpanded,
                        onDismissRequest = { isAscendingListDropDownExpanded = false },
                        modifier = Modifier
                            .width(155.dp)
                            .background(Color.White)
                    ) {
                        ascendingList.forEach {
                            DropdownMenuItem(onClick = {
                                ascendingListVal = it
                                onOrderChange(
                                    noteOrder.copy(
                                        if (it.lowercase(Locale.getDefault()) == "ascending") {
                                            OrderType.Ascending
                                        } else {
                                            OrderType.Descending
                                        }
                                    )
                                )
                                isAscendingListDropDownExpanded = false
                            }, modifier = Modifier.fillMaxWidth(0.8f)) {
                                Text(
                                    it,
                                    fontFamily = FontFamily(Font(R.font.google_sans_medium)),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}