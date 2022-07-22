package com.narcis.openweatherinterview.ui.places

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun mapView(
weatherViewModel: WeatherViewModel,
onBackClicked : () -> Unit
) {
    val locationPermission = rememberPermissionState(permission =
            android.Manifest.permission.ACCESS_FINE_LOCATION)

    PermissionRequired(
        permissionState = locationPermission,
        permissionNotGrantedContent = {  },
        permissionNotAvailableContent = {}) {

     weatherViewModel.getWeatherByLat()

    }
}

@Composable
fun locationNotGranted(onButtonClicked: () -> Unit) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (title, Button) = createRefs()

        Text(text = "Location permission is needed for this feature",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = parent.bottom
                    )
                }
        )

        Button(
            onClick = {onButtonClicked()},
            modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 32.dp)
            .constrainAs(Button) {
                linkTo(
                    start = parent.start,
                    end = parent.end
                )
                top.linkTo(title.bottom)
            }
            ) {
            Text(text = "Grant Permission")
        }
    }

}
@Preview
@Composable
fun previewLocationNotGranted() {locationNotGranted {}}