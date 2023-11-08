package com.example.ibird

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ibird.databinding.ActivityNavigationBinding
import com.mapbox.maps.Style
import com.mapbox.navigation.dropin.ViewOptionsCustomization.Companion.defaultRouteArrowOptions
import com.mapbox.navigation.dropin.ViewOptionsCustomization.Companion.defaultRouteLineOptions
import com.mapbox.navigation.ui.maps.NavigationStyles
import com.mapbox.navigation.ui.maps.route.RouteLayerConstants
import com.mapbox.navigation.ui.maps.route.arrow.model.RouteArrowOptions
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineOptions
import com.mapbox.navigation.ui.maps.route.line.model.RouteLineColorResources
import com.mapbox.navigation.ui.maps.route.line.model.RouteLineResources


class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private var toggleCustomOptions = false

    /*private val routeLineOptions: MapboxRouteLineOptions by lazy {
        MapboxRouteLineOptions.Builder(this)
            .withRouteLineResources(
                RouteLineResources.Builder()
                    .routeLineColorResources(
                        RouteLineColorResources.Builder()
                            .routeLowCongestionColor(Color.YELLOW)
                            .routeCasingColor(Color.RED)
                            .build()
                    )
                    .build()
            )
// make sure to use the correct layerId based on the map styles
            .withRouteLineBelowLayerId("road-label") // for Style.LIGHT and Style.DARK
            .withVanishingRouteLineEnabled(true)
            .displaySoftGradientForTraffic(true)
            .build()
    }*/

    private val routeArrowOptions by lazy {
        RouteArrowOptions.Builder(this)
            .withAboveLayerId(RouteLayerConstants.TOP_LEVEL_ROUTE_LINE_LAYER_ID)
            .withArrowColor(Color.RED)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationView.api.routeReplayEnabled(true)

        /*binding.toggleOptions.setOnClickListener {
            binding.navigationView.customizeViewOptions {
                toggleCustomOptions = if (toggleCustomOptions) {
                    routeLineOptions = defaultRouteLineOptions(applicationContext)
                    routeArrowOptions = defaultRouteArrowOptions(applicationContext)
                    mapStyleUriDay = NavigationStyles.NAVIGATION_DAY_STYLE
                    mapStyleUriNight = NavigationStyles.NAVIGATION_NIGHT_STYLE
                    false
                } else {
                    routeLineOptions = this@NavigationActivity.routeLineOptions
                    routeArrowOptions = this@NavigationActivity.routeArrowOptions
                    mapStyleUriDay = Style.LIGHT
                    mapStyleUriNight = Style.DARK
                    true
                }
            }
        }*/
    }
}