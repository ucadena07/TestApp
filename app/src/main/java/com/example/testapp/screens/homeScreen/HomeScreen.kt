package com.example.testapp.screens.homeScreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Handyman
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.testapp.authentication.AuthState
import com.example.testapp.components.buyers.BuyerCard
import com.example.testapp.components.shared.CameraPreview
import com.example.testapp.components.shared.NkTextField
import com.example.testapp.components.shared.PhotoBottomSheetContent
import com.example.testapp.layout.MainLayout

import com.example.testapp.model.Buyer
import com.example.testapp.navigation.ApplicationScreens
import com.example.testapp.utils.formatApiDateToTimestamp
import com.example.testapp.utils.formatDate
import com.example.testapp.utils.formatDateTime
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeScreenViewModel, context: Context = LocalContext.current) {
    val tabItems = listOf(
        TabItem(
            title = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon =  Icons.Filled.Home
        ),
        TabItem(
            title = "Camera",
            unselectedIcon = Icons.Outlined.Camera,
            selectedIcon =  Icons.Filled.Camera
        ),
        TabItem(
            title = "Utils",
            unselectedIcon = Icons.Outlined.Handyman,
            selectedIcon =  Icons.Filled.Handyman
        )
    )

    val bitmaps by homeViewModel.bitmaps.collectAsState()
    val scope = rememberCoroutineScope()

    MainLayout(navController = navController, isHome = true) {
        var selectedTabIndex by remember {
            mutableIntStateOf(0)
        }
        val pagerState = rememberPagerState {
            tabItems.size
        }
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if(!pagerState.isScrollInProgress){
                selectedTabIndex = pagerState.currentPage
            }

        }
        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = { Text(text = tabItem.title) },
                        icon = {
                            Icon(
                                imageVector = if(index == selectedTabIndex){
                                                                      tabItem.selectedIcon
                                                                           }else tabItem.unselectedIcon,
                                contentDescription = ""
                            )
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {index ->
                Box(modifier = Modifier.fillMaxSize()){
                    if(index == 0){
                        HomeContent(homeViewModel = homeViewModel, navController = navController)
                    }
                    if(index == 1){
                      var scaffoldState = rememberBottomSheetScaffoldState()
                        var controller = remember {
                            LifecycleCameraController(context).apply {
                                setEnabledUseCases(
                                    CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
                                )
                            }
                        }
                        BottomSheetScaffold(
                            scaffoldState = scaffoldState,
                            sheetPeekHeight = 0.dp,
                            sheetContent = {
                                PhotoBottomSheetContent(bitmaps);
                            }) {padding ->
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)){
                                CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())
                                IconButton(
                                    onClick = {
                                        controller.cameraSelector = if(controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA){
                                            CameraSelector.DEFAULT_FRONT_CAMERA
                                        }else{
                                            CameraSelector.DEFAULT_BACK_CAMERA
                                        }
                                    },
                                    modifier = Modifier.offset(16.dp,16.dp),
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)

                                ) {
                                    Icon(imageVector = Icons.Default.Cameraswitch, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)
                                }
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(16.dp), horizontalArrangement = Arrangement.SpaceAround) {
                                    IconButton(onClick = {
                                        scope.launch {
                                            scaffoldState.bottomSheetState.expand()
                                        }
                                    }) {
                                        Icon(imageVector = Icons.Default.Photo, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)
                                    }
                                    IconButton(onClick = { takePhoto(controller,context){
                                        homeViewModel.onTakePhoto(it)
                                    } }) {
                                        Icon(imageVector = Icons.Default.PhotoCamera, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)
                                    }
                                }
                            }
                        }
                    }
                    if(index == 2){
                        Text(text = "Tab 3")
                    }
                }
            }
        }
    }
}

//Column(modifier = Modifier.padding(3.dp)) {
//
//    Text(text = "Buyers", style = MaterialTheme.typography.headlineMedium)
//    //NkTextField(label = "Search")
//    Spacer(modifier = Modifier.height(10.dp))
//    LazyColumn {
//        items(homeViewModel.buyers.value!!){
//            BuyerCard(buyer = it){
//                navController.navigate("${ApplicationScreens.BuyerFormScreen}/${it.buyerID}")
//            }
//        }
//    }
//}

fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Bitmap) -> Unit
){
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : OnImageCapturedCallback(){
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                val matrix = Matrix().apply{
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotateBitMap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )
                onPhotoTaken(rotateBitMap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("CAMERA", "couldnt take photo")
            }
        }
    )
}


data class  TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)
@Composable
fun HomeContent(homeViewModel: HomeScreenViewModel, navController: NavController){
    Column(modifier = Modifier.padding(3.dp)) {

    Text(text = "Buyers", style = MaterialTheme.typography.headlineMedium)
    //NkTextField(label = "Search")
    Spacer(modifier = Modifier.height(10.dp))
    LazyColumn {
        items(homeViewModel.buyers.value!!){
            BuyerCard(buyer = it){
                navController.navigate("${ApplicationScreens.BuyerFormScreen}/${it.buyerID}")
            }
        }
    }
}
}