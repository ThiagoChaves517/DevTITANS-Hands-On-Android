package com.example.plaintext.ui.screens.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.plaintext.R
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip

// Composable que representa uma linha com uma imagem e um texto
@Composable
fun CustomImageTextRow(
    backgroundColor: Color = colorResource(id = R.color.login_row_background),
    imageResId: Int = R.drawable.ic_launcher_foreground,
    contentDescription: String,
    textResId: Int,
    imageStartPadding: Dp = 25.dp,
    textImageSpacerWidth: Dp = 8.dp,
    textEndPadding: Dp = 25.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier.padding(start = imageStartPadding)
        )

        Spacer(Modifier.width(textImageSpacerWidth))

        Text(
            text = stringResource(id = textResId),
            modifier = Modifier.padding(end = textEndPadding)
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
// Entradas de texto para o login e password
fun LoginInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector = Icons.Default.AccountBox,
    contentColor: Color = Color.White,
    isPasswordInput: Boolean = false
) {
    val visualTransformation = if (isPasswordInput) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "Icone de Entradada de Texto"
            )
        },
        modifier = modifier,
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = contentColor,
            unfocusedTextColor = contentColor,
            focusedLeadingIconColor = contentColor,
            unfocusedLeadingIconColor = contentColor,
            focusedLabelColor = contentColor,
            unfocusedLabelColor = contentColor,
            focusedBorderColor = contentColor,
            unfocusedBorderColor = contentColor
        )
    )
}

@Composable
// Caixa de seleção de opção com texto
fun CheckableRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    spacerWidth: Dp = 5.dp,
    contentColor: Color = Color.White
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = contentColor,
                uncheckedColor = contentColor
            )
        )
        Spacer(Modifier.width(spacerWidth))
        Text(text = text)
    }
}

@Composable
// Botão com ícones à esquerda e à direita
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?,
    leadingIconRotation: Float = 0f,
    trailingIconRotation: Float = 0f,
    spacerWidth: Dp = 8.dp,
    buttonColor: Color = colorResource(id = R.color.login_button)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone da esquerda
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Ícone à esquerda do texto",
                    modifier = Modifier.rotate(leadingIconRotation)
                )
            }

            // Espaçamento e texto
            if (leadingIcon != null && trailingIcon != null) {
                Spacer(modifier = Modifier.width(spacerWidth))
            }

            Text(
                text = text.uppercase(),
                fontWeight = FontWeight.Bold
            )

            // Espaçamento e ícone da direita
            if (leadingIcon != null && trailingIcon != null) {
                Spacer(modifier = Modifier.width(spacerWidth))
            }
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "Ícone à direita do texto",
                    modifier = Modifier.rotate(trailingIconRotation)
                )
            }
        }
    }
}

@Composable
fun CustomSnackbarHost(snackbarHostState: SnackbarHostState) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .alpha(0.9f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AndroidIconWithRoundedImageBackground()
                Text(
                    text = data.visuals.message,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun AndroidIconWithRoundedImageBackground() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(52.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Imagem de fundo",
            modifier = Modifier
                .size(52.dp)
                .clip(shape = RoundedCornerShape(6.dp))
        )
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = "Ícone do Android",
            tint = MaterialTheme.colorScheme.onErrorContainer,
            modifier = Modifier.size(36.dp)
        )
    }
}
