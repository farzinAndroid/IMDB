package com.farzin.imdb.ui.service

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.appBackGround
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.viewmodel.DataStoreViewModel

@Composable
fun ServiceScreen(navController: NavController) {

    Service(navController)

}

@Composable
fun Service(
    navController: NavController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {

    val serviceList = listOf<ServiceModel>(
        ServiceModel(
            name = stringResource(R.string.netflix),
            logo = painterResource(R.drawable.netflix),
            id = 213
        ),
        ServiceModel(
            name = stringResource(R.string.cbc),
            logo = painterResource(R.drawable.cbc),
            id = 201
        ),
        ServiceModel(
            name = stringResource(R.string.nbc),
            logo = painterResource(R.drawable.nbc),
            id = 6
        ),
        ServiceModel(
            name = stringResource(R.string.cnbc),
            logo = painterResource(R.drawable.cnbc),
            id = 175
        ),
        ServiceModel(
            name = stringResource(R.string.abc),
            logo = painterResource(R.drawable.abc),
            id = 2
        ),
        ServiceModel(
            name = stringResource(R.string.bbc),
            logo = painterResource(R.drawable.bbc),
            id = 3
        ),
        ServiceModel(
            name = stringResource(R.string.fox),
            logo = painterResource(R.drawable.fox_1),
            id = 19
        ),
        ServiceModel(
            name = stringResource(R.string.hbo),
            logo = painterResource(R.drawable.hbo),
            id = 49
        ),
        ServiceModel(
            name = stringResource(R.string.cn),
            logo = painterResource(R.drawable.cn),
            id = 56
        ),
        ServiceModel(
            name = stringResource(R.string.amc),
            logo = painterResource(R.drawable.amc),
            id = 174
        ),
        ServiceModel(
            name = stringResource(R.string.none),
            logo = null,
            id = 0
        ),
    )

    var selectedService by remember { mutableStateOf<ServiceModel?>(null) }

    dataStoreViewModel.getServiceId()?.let { id ->
        selectedService = serviceList.find { it.id == id }
    }


    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.appBackGround)
    ) {

        item { ServiceTopBarSection(navController) }
        item { ServiceDescription() }
        item { MyDividerHorizontal() }
        items(serviceList) { service ->
            ServiceItem(
                item = service,
                isSelected = service == selectedService,
                onClick = {
                    selectedService = service
                    Toast.makeText(context, service.id.toString(), Toast.LENGTH_SHORT).show()
                    dataStoreViewModel.saveServiceId(service.id)
                }
            )
        }

    }

}