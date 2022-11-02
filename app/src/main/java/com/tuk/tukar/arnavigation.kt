package com.tuk.tukar


import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mapbox.android.core.location.*
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.core.constants.Constants
import com.mapbox.geojson.Point
import com.mapbox.geojson.utils.PolylineUtils
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigationOptions
import com.mapbox.services.android.navigation.v5.offroute.OffRouteListener
import com.mapbox.services.android.navigation.v5.route.RouteFetcher
import com.mapbox.services.android.navigation.v5.route.RouteListener
import com.mapbox.services.android.navigation.v5.routeprogress.ProgressChangeListener
import com.mapbox.services.android.navigation.v5.routeprogress.RouteProgress
import com.mapbox.vision.VisionManager
import com.mapbox.vision.ar.VisionArManager
import com.mapbox.vision.ar.core.models.ManeuverType
import com.mapbox.vision.ar.core.models.Route
import com.mapbox.vision.ar.core.models.RoutePoint
import com.mapbox.vision.ar.view.gl.VisionArView
import com.mapbox.vision.mobile.core.interfaces.VisionEventsListener
import com.mapbox.vision.mobile.core.models.position.GeoCoordinate
import com.mapbox.vision.performance.ModelPerformance
import com.mapbox.vision.performance.ModelPerformanceConfig
import com.mapbox.vision.performance.ModelPerformanceMode
import com.mapbox.vision.performance.ModelPerformanceRate
import com.mapbox.vision.utils.VisionLogger
import kotlinx.android.synthetic.main.activity_arnavigation.*
import com.mapbox.vision.ar.FenceVisualParams
import com.mapbox.vision.ar.LaneVisualParams
import com.mapbox.vision.ar.core.models.Color


class Arnavigation : AppCompatActivity(),
    RouteListener, ProgressChangeListener,
    OffRouteListener {

    companion object {
        private var TAG = Arnavigation::class.java.simpleName

        private const val LOCATION_INTERVAL_DEFAULT = 0L
        private const val LOCATION_INTERVAL_FAST = 1000L
        private const val PERMISSION_FOREGROUND_SERVICE = "android.permission.FOREGROUND_SERVICE"
        private const val PERMISSIONS_REQUEST_CODE = 123
        var directionsRoute: DirectionsRoute? = null

        fun start(context: Activity) {
            context.startActivity(Intent(context, Arnavigation::class.java))
        }
    }
    private lateinit var mapboxNavigation: MapboxNavigation
    private lateinit var routeFetcher: RouteFetcher
    private lateinit var lastRouteProgress: RouteProgress
    private var visionManagerWasInit = false
    private var navigationWasStarted = false

    private val arLocationEngine by lazy {
        LocationEngineProvider.getBestLocationEngine(this)
    }

    private val locationCallback by lazy {
        object : LocationEngineCallback<LocationEngineResult> {
            override fun onSuccess(result: LocationEngineResult?) {
            }

            override fun onFailure(exception: Exception) {
            }
        }
    }
    private val arLocationEngineRequest by lazy {
        LocationEngineRequest.Builder(LOCATION_INTERVAL_DEFAULT)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .setFastestInterval(LOCATION_INTERVAL_FAST)
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_arnavigation)

        // Initialize navigation with your Mapbox access token.
        val builder = MapboxNavigationOptions
            .builder()
        mapboxNavigation = MapboxNavigation(
            this,
            getString(R.string.mapbox_access_token),
            builder.build()
        )

        routeFetcher = RouteFetcher(this, getString(R.string.mapbox_access_token))
        routeFetcher.addRouteListener(this)
        if (!allPermissionsGranted() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(getRequiredPermissions()!!, PERMISSIONS_REQUEST_CODE);
        } else {
            startVisionManager()
            startNavigation()
        }

    }
    override fun onErrorReceived(throwable: Throwable?) {
        throwable?.printStackTrace()

        mapboxNavigation.stopNavigation()
        Toast.makeText(this, "Can not calculate the route requested", Toast.LENGTH_SHORT).show()
    }

    override fun onResponseReceived(response: DirectionsResponse, routeProgress: RouteProgress?) {
        mapboxNavigation.stopNavigation()
        if (response.routes().isEmpty()) {
            Toast.makeText(this, "Can not calculate the route requested", Toast.LENGTH_SHORT).show()
        }
        lastRouteProgress = routeProgress!!

        setRoute(response.routes()[0])
    }
    override fun onProgressChange(location: Location, routeProgress: RouteProgress) {
        lastRouteProgress = routeProgress
    }

    override fun userOffRoute(location: Location) {
        routeFetcher.findRouteFromRouteProgress(location, lastRouteProgress)
    }
    private fun DirectionsRoute.getRoutePoints(): Array<RoutePoint> {
        val routePoints = arrayListOf<RoutePoint>()
        legs()?.forEach { it ->
            it.steps()?.forEach { step ->
                val maneuverPoint = RoutePoint(
                    GeoCoordinate(
                        latitude = step.maneuver().location().latitude(),
                        longitude = step.maneuver().location().longitude()
                    )
                )
                routePoints.add(maneuverPoint)

                step.intersections()
                    ?.map {
                        RoutePoint(
                            GeoCoordinate(
                                latitude = step.maneuver().location().latitude(),
                                longitude = step.maneuver().location().longitude()
                            )
                        )
                    }
                    ?.let { stepPoints ->
                        routePoints.addAll(stepPoints)
                    }
            }
        }

        return routePoints.toTypedArray()
    }

    protected fun setArRenderOptions(visionArView: VisionArView) {
        visionArView.setLaneVisible(true)
        VisionArManager.setLaneLength(40f)

        val laneVisualParams = LaneVisualParams(
            color = Color(1.0f, 0.0f, 0.0f, 1.0f),
            width = 1f,
            arrowLength = 2.5f
        )
        visionArView.setLaneVisualParams(laneVisualParams)
        visionArView.setFenceVisible(true)

        val fenceVisualParams = FenceVisualParams(
            color = Color(1.0f, 1.0f, 0.0f, 1.0f),
            height = 2f,
            verticalOffset = 1f,
            horizontalOffset = 3f,
            sectionsCount = 3
        )
        visionArView.setFenceVisualParams(fenceVisualParams)
        visionArView.setArQuality(0.8f)

    }
    private fun String.buildStepPointsFromGeometry(): List<Point> {
        return PolylineUtils.decode(this, Constants.PRECISION_6)
    }

    private fun setRoute(route: DirectionsRoute) {
        mapboxNavigation.startNavigation(route)


        VisionArManager.setRoute(Route(
            points = route.getRoutePoints(),
            eta = route.duration()?.toFloat() ?: 0f,
            "Source Location",
            "Destination Location"
        )
        )
    }

    override fun onStart() {
        super.onStart()
        startVisionManager()
        startNavigation()
    }
    override fun onStop() {
        super.onStop()
        stopVisionManager()
        stopNavigation()
    }
    private fun allPermissionsGranted(): Boolean {
        for (permission in getRequiredPermissions()) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission!!
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // PERMISSION_FOREGROUND_SERVICE was added for targetSdkVersion >= 28, it is normal and always granted, but should be added to the Manifest file
                // on devices with Android < P(9) checkSelfPermission(PERMISSION_FOREGROUND_SERVICE) can return PERMISSION_DENIED, but in fact it is GRANTED, so skip it
                // https://developer.android.com/guide/components/services#Foreground
                if (permission == PERMISSION_FOREGROUND_SERVICE) {
                    continue
                }
                return false
            }
        }
        return true
    }
    private fun getRequiredPermissions(): Array<String?> {
        val permissions: Array<String?> = try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
            val requestedPermissions = info.requestedPermissions
            if (requestedPermissions != null && requestedPermissions.isNotEmpty()) {
                requestedPermissions
            } else {
                arrayOf()
            }
        } catch (e: NameNotFoundException) {
            arrayOf()
        }
        return permissions
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (allPermissionsGranted() && requestCode == PERMISSIONS_REQUEST_CODE) {
            startVisionManager()
            startNavigation()
        }
    }

    private fun startVisionManager() {
        if (allPermissionsGranted() && !visionManagerWasInit) {
            // Create and start VisionManager.
            VisionManager.create()
            VisionManager.setModelPerformanceConfig(
                ModelPerformanceConfig.Merged(
                    performance = ModelPerformance.On(
                        ModelPerformanceMode.DYNAMIC,
                        ModelPerformanceRate.HIGH)
                )
            )
            VisionManager.start()
            VisionManager.visionEventsListener = object : VisionEventsListener {}

            VisionArManager.create(VisionManager)
            mapbox_ar_view.setArManager(VisionArManager)
            setArRenderOptions(mapbox_ar_view)
            directionsRoute.let {
                if (it == null) {
                    Toast.makeText(this, "Route is not set!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    setRoute(it)
                }
            }
            visionManagerWasInit = true
        }
    }
    private fun stopVisionManager() {
        if (visionManagerWasInit) {
            VisionArManager.destroy()
            VisionManager.stop()
            VisionManager.destroy()

            visionManagerWasInit = false
        }
    }

    private fun startNavigation() {
        if (allPermissionsGranted() && !navigationWasStarted) {
            // Initialize navigation with your Mapbox access token.
            mapboxNavigation = MapboxNavigation(
                this,
                getString(R.string.mapbox_access_token),
                MapboxNavigationOptions.builder().build()
            )

            // Initialize route fetcher with your Mapbox access token.
            routeFetcher = RouteFetcher(this, getString(R.string.mapbox_access_token))
            routeFetcher.addRouteListener(this)

            try {
                arLocationEngine.requestLocationUpdates(
                    arLocationEngineRequest,
                    locationCallback,
                    mainLooper
                )
            } catch (se: SecurityException) {
                VisionLogger.e(TAG, se.toString())
            }

            // Route need to be reestablished if off route happens.
            mapboxNavigation.addOffRouteListener(this)
            mapboxNavigation.addProgressChangeListener(this)

            navigationWasStarted = true
        }
    }

    private fun stopNavigation() {
        if (navigationWasStarted) {
            arLocationEngine.removeLocationUpdates(locationCallback)

            mapboxNavigation.removeProgressChangeListener(this)
            mapboxNavigation.removeOffRouteListener(this)
            mapboxNavigation.stopNavigation()

            navigationWasStarted = false
        }
    }


}