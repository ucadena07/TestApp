package com.example.testapp.components.shared
import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lock

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.size.Size
import com.example.testapp.helpers.connectivity.IConnectivityObserver
import com.example.testapp.helpers.connectivity.NetworkConnectivityObserver
import com.example.testapp.navigation.ApplicationScreens
import com.example.testapp.ui.theme.BgColor
import kotlinx.coroutines.launch


@Composable
fun NormalTextComponent(value:String, modifier: Modifier = Modifier){
    Text(
        text = value,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        ),
    )
}
@Composable
fun HeadingTextComponent(value:String, modifier: Modifier = Modifier){
    Text(
        text = value,
        modifier = modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        ),
    )
}
@Composable
fun NkTextField(
    label: String,
    icon: ImageVector? = null,
    isError: Boolean = false,
    onValueChange: (String) -> Unit = { } ,
    value: String? = "",
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    onAction: KeyboardActions = KeyboardActions.Default
    ){
    val textValue = rememberSaveable{
        mutableStateOf(value)
    }


    textValue.value?.let {
        OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            ,
        label =  {Text(text = label) },
        value = it,
        onValueChange = {value ->
            textValue.value = value
            onValueChange(value)
        },
         isError = isError,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = Color.DarkGray,
            unfocusedTextColor = Color.DarkGray,

        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),

        leadingIcon =
            if(icon != null){
                {Icon(imageVector = icon, contentDescription = "")}
            } else{
                null
            }

    )
    }
}
@Composable
fun NkPasswordTextField(label: String,
                        isError: Boolean = false,
                        onValueChange: (String) -> Unit = { } ,
                        value: String? = "",){
    val textValue = rememberSaveable{
        mutableStateOf(value)
    }

    val passwordVisible =rememberSaveable{
        mutableStateOf(false)
    }


    textValue.value?.let {
        OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label =  {Text(text = label) },
        value = it,
        onValueChange = {value ->
            textValue.value = value
            onValueChange(value)
        }, isError = isError,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,

            ),
        keyboardOptions = KeyboardOptions(

            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        leadingIcon = {

            Icon(imageVector = Icons.Default.Lock, contentDescription = "")


        },
        trailingIcon = {
            if(passwordVisible.value){
                IconButton(onClick = {passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = Icons.Default.Visibility, contentDescription = "")
                }

            }else{
                IconButton(onClick = {passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "")
                }

            }

        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
    }
}
@Composable
fun NkButton(value: String, color: Color? =  null, enabled: Boolean = true,loading: Boolean = false, onClick: () -> Unit = {}){


    Button(onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(Color.Transparent)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(

                        if (color != null) {
                            listOf(
                                color,
                                color
                            )
                        } else {
                            listOf(
                                MaterialTheme.colorScheme.primaryContainer,
                                MaterialTheme.colorScheme.primary
                            )
                        }

                    ),
                    shape = RoundedCornerShape(30.dp)
                ),
            contentAlignment = Alignment.Center,

        ){
            if(!loading){
                Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }else{
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(32.dp))
            }


        }

    }
}
@Composable
fun UnderlineTextComponent(value:String, modifier: Modifier = Modifier, onClick: () -> Unit = {}){
    Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = value,
            modifier = modifier
                .heightIn(min = 40.dp)
                .clickable {
                    onClick();
                },
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                color = Color.LightGray
            ),
            textDecoration = TextDecoration.Underline
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTooBar(
    title: String,
    icon: ImageVector? = null,
    showProfile: Boolean,
    isHome: Boolean,
    navController: NavController,
    onBackArrowClicked: () -> Unit = {}
){
    val ctx = LocalContext.current
    lateinit var connectivityObserver : IConnectivityObserver
    connectivityObserver = NetworkConnectivityObserver(ctx)
    val status by connectivityObserver.observe().collectAsState(initial = IConnectivityObserver.ConnectivityStatus.Offline)
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = { Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimary) },
        navigationIcon = {
                            if(!isHome && icon == null){
                                Icon(imageVector = Icons.Default.Home, tint = MaterialTheme.colorScheme.onPrimary, contentDescription = "",
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .clickable {
                                            navController.navigate(ApplicationScreens.HomeScreen.name)
                                        })
                            }else{
                                if(icon != null){
                                    Icon(imageVector = icon, tint = MaterialTheme.colorScheme.onPrimary, contentDescription = "",
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp)
                                            .clickable {
                                                onBackArrowClicked()
                                            })
                                }
                            }
                         },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = if(status == IConnectivityObserver.ConnectivityStatus.Online) Icons.Filled.Wifi else Icons.Filled.WifiOff,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = { navController.navigate(ApplicationScreens.AccountDetailsScreen.name)}) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Localized description",

                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        },

        )
}

@Composable
fun Toast(){
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


}

@Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
){
    val lifeCycleOwner = LocalLifecycleOwner.current
    AndroidView(factory = {
        PreviewView(it).apply {
            this.controller = controller
            controller.bindToLifecycle(lifeCycleOwner)
        }
    },
        modifier = modifier)
}

@Composable
fun PhotoBottomSheetContent(
    bitmaps: List<Bitmap>,
    modifier: Modifier = Modifier
){
    if(bitmaps.isEmpty()){
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center){
            Text(text = "No Images Found")
        }
    }else{
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {

            items(bitmaps){
                Image(bitmap = it.asImageBitmap(), contentDescription = "", modifier = Modifier.clip(
                    RoundedCornerShape(10.dp)))
            }
        }

    }
}

@Composable
fun PermissionDialog(
    permissionTextProvider: IPermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick:() -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
               Button(onClick = {
                   if(isPermanentlyDeclined) onGoToAppSettingsClick() else onOkClick()
               }) {
                   Text(text = if(isPermanentlyDeclined) "Grant Permission" else "OK")
               }         
        },
        dismissButton = {

        },
        title = {
                Text(text = "Permission Required")
        },
        text = {
                Text(text = permissionTextProvider.getDescription(isPermanentlyDeclined))
        },
        modifier =  modifier
    )
}

interface  IPermissionTextProvider{
   fun getDescription(isPermanentlyDeclined: Boolean) : String
}

class CameraPermissionTextProvider : IPermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined){
            "It seems you permanently declined camera permission. " + "You can go to the app settings to grant it."
        }else{
            "This app needs access access to your camera."
        }
    }

}
class AudioPermissionTextProvider : IPermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined){
            "It seems you permanently declined camera permission. " + "You can go to the app settings to grant it."
        }else{
            "This app needs access access to your microphone."
        }
    }

}

class PhonePermissionTextProvider : IPermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined){
            "It seems you permanently declined camera permission. " + "You can go to the app settings to grant it."
        }else{
            "This app needs access access to your phone calling permission."
        }
    }

}