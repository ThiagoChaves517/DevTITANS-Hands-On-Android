package com.example.plaintext.ui.screens.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plaintext.R
import com.example.plaintext.data.model.PasswordInfo

@Composable
fun ListItem(
    password: PasswordInfo,
    navigateToEdit: (password: PasswordInfo) -> Unit
) {
    val title = password.name
    val subTitle = password.login

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { navigateToEdit(password) }
            .padding(vertical = 1.dp)
            .padding(horizontal = 10.dp)
            .background(colorResource(R.color.login_button)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .weight(.7f)
                .background(
                    color = colorResource(R.color.login_row_background),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(all = 5.dp),

            ) {
            Text(title, fontSize = 20.sp)
            Text(subTitle, fontSize = 14.sp)
        }
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Menu",
            tint = Color.White
        )
    }
}