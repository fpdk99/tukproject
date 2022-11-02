package com.tuk.tukar

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.location.LocationEngineRequest
import com.mapbox.android.core.location.LocationEngineResult
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import com.mapbox.vision.VisionManager.init
import com.mapbox.vision.utils.VisionLogger
import kotlinx.android.synthetic.main.activity_arnavigation_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.tuk.tukar.LocationPermissionHelper
import java.lang.ref.WeakReference


class Arnavigationsearch : AppCompatActivity(), OnMapReadyCallback, PermissionsListener, MapboxMap.OnMapClickListener {

    companion object {
        private var TAG = Arnavigationsearch::class.java.simpleName
    }
    private val REQUEST_CODE_AUTOCOMPLETE = 7171
    private var originPoint: Point? = null
    private lateinit var mapboxMap: MapboxMap
    private var destinationMarker: Marker? = null
    private var currentRoute: DirectionsRoute? = null
    private var navigationMapRoute: NavigationMapRoute? = null
    private var locationComponent: LocationComponent? = null
    private val geoJsonSourceLayerId = "GeoJsonSourceLayerId"
    private val symbolIconId = "SymbolIconId"

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
    }
    override fun onPermissionResult(granted: Boolean) {
        if (granted) {

        } else {

        }
    }

    private val arLocationEngine by lazy {
        LocationEngineProvider.getBestLocationEngine(this)
    }

    private val arLocationEngineRequest by lazy {
        LocationEngineRequest.Builder(0)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .build()
    }
    private val locationCallback by lazy {
        object : LocationEngineCallback<LocationEngineResult> {
            override fun onSuccess(result: LocationEngineResult?) {
                with(result as LocationEngineResult) {
                    originPoint = Point.fromLngLat(lastLocation?.longitude ?: .0, lastLocation?.latitude ?: .0)
                }
            }

            override fun onFailure(exception: Exception) {
            }
        }
    }
    var permissionsManager = PermissionsManager(this@Arnavigationsearch)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arnavigation_search)

        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Permission sensitive logic called here, such as activating the Maps SDK's LocationComponent to show the device's location

        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)
        }

        fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>,
            grantResults: IntArray
        ) {
            permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

        if (ContextCompat.checkSelfPermission(this@Arnavigationsearch, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            getPermission()
        }
        mapView.onCreate(savedInstanceState)



        btnStartwithar.setOnClickListener {
            if (currentRoute == null) {
                Toast.makeText(this, "Route is not ready yet!", Toast.LENGTH_LONG).show()
            } else {
                Arnavigation.directionsRoute = currentRoute
                Arnavigation.start(this)
                val intent = Intent(this@Arnavigationsearch, Arnavigation::class.java)
                startActivity(intent)
            }
        }

    }
    private fun getPermission() {
        if (ContextCompat.checkSelfPermission(this@Arnavigationsearch,
                Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@Arnavigationsearch,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this@Arnavigationsearch,
                    arrayOf(Manifest.permission.CAMERA),
                    50)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            50 -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                } else {

                }
                return
            }


            else -> {

            }
        }
    }
    private fun addDestinationIconSymbolLayer(loadedMapStyle: Style?) {
        loadedMapStyle!!.addImage("destination-icon-id", BitmapFactory.decodeResource(this.resources, R.drawable.red_marker))

        val geoJsonSource = GeoJsonSource("destination-source-id")
        loadedMapStyle.addSource(geoJsonSource)
        val destinationSymbolLayer = SymbolLayer("destination-symbol-layer-id", "destination-source-id")
        destinationSymbolLayer.withProperties(PropertyFactory.iconImage("destination-icon-id"),
            PropertyFactory.iconAllowOverlap(true),
            PropertyFactory.iconIgnorePlacement(true))

        loadedMapStyle.addLayer(destinationSymbolLayer)
    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
        mapView.getMapAsync(this)
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        arLocationEngine.removeLocationUpdates(locationCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }
    override fun onMapClick(destination: LatLng): Boolean {
        destinationMarker?.let(mapboxMap::removeMarker)
        destinationMarker = mapboxMap.addMarker(MarkerOptions().position(destination))

        if (originPoint == null) {
            Toast.makeText(this, "Source location is not determined yet!", Toast.LENGTH_LONG).show()
            return false
        }

        getRoute(
            origin = originPoint!!,
            destination = Point.fromLngLat(destination.longitude, destination.latitude)
        )

        btnStartwithar.visibility = View.VISIBLE
        btnStart!!.isEnabled = true
        btnStart!!.setBackgroundResource(R.color.mapboxBlue)
        return true
    }
    override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap
        mapboxMap.setStyle(getString(com.mapbox.services.android.navigation.ui.v5.R.string.navigation_guidance_day)) { style: Style? ->
            enableLocationComponent()

            addDestinationIconSymbolLayer(style)
            mapboxMap.addOnMapClickListener(this)
            btnStart.setOnClickListener { v: View? ->
                val simulateRoute = true
                val options = NavigationLauncherOptions.builder()
                    .directionsRoute(currentRoute)
                    .shouldSimulateRoute(simulateRoute)
                    .build()

                NavigationLauncher.startNavigation(this, options)
            }
            initSearchFab()
            setUpSource(style!!)
            setUpLayer(style!!)

            val drawable =
                ResourcesCompat.getDrawable(resources, R.drawable.ic_location_on_red_24dp, null)
            val bitmapUtils = BitmapUtils.getBitmapFromDrawable(drawable)
            style!!.addImage(symbolIconId, bitmapUtils!!)
        }
    }
    private fun setUpLayer(loadedMapStyle: Style) {
        loadedMapStyle.addLayer(SymbolLayer("SYMBOL_LAYER_ID", geoJsonSourceLayerId).withProperties(
            PropertyFactory.iconImage(symbolIconId),
            PropertyFactory.iconOffset(arrayOf(0f, -8f))
        ))
    }
    private fun setUpSource(loadedMapStyle: Style) {
        loadedMapStyle.addSource(GeoJsonSource(geoJsonSourceLayerId))
    }

    private fun initSearchFab() {
        fab_location_search.setOnClickListener{v: View? ->
            val intent = PlaceAutocomplete.IntentBuilder()
                .accessToken(
                    (if (Mapbox.getAccessToken() != null) Mapbox.getAccessToken() else getString(R.string.mapbox_access_token))!!
                ).placeOptions(
                    PlaceOptions.builder()
                        .backgroundColor(Color.parseColor("#EEEEEE"))
                        .limit(10)
                        .build(PlaceOptions.MODE_CARDS))
                .build(this@Arnavigationsearch)
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            val selectedCarmenFeature = PlaceAutocomplete.getPlace(data)
            if (mapboxMap != null) {
                val style = mapboxMap!!.style
                if (style != null) {
                    val source = style.getSourceAs<GeoJsonSource>(geoJsonSourceLayerId)
                    source?.setGeoJson(FeatureCollection.fromFeatures(arrayOf(Feature.fromJson(selectedCarmenFeature.toJson()))))
                    mapboxMap!!.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                .target(LatLng((selectedCarmenFeature.geometry() as Point?)!!.latitude(),
                                    (selectedCarmenFeature.geometry() as Point?)!!.longitude()))
                                .zoom(16.5)
                                .build()), 4000)
                }
            }
        }
    }
    private fun getRoute(origin: Point, destination: Point) {
        NavigationRoute.builder(this)
            .accessToken(Mapbox.getAccessToken()!!)
            .origin(origin)
            .destination(destination)
            .build()
            .getRoute(object : Callback<DirectionsResponse> {
                override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {
                    if (response.body() == null || response.body()!!.routes().size < 1) {
                        return
                    }

                    currentRoute = response.body()!!.routes()[0]

                    // Draw the route on the map
                    if (navigationMapRoute != null) {
                        Toast.makeText(this@Arnavigationsearch, "Unable to set route visibility", Toast.LENGTH_LONG)
                    } else {
                        navigationMapRoute = NavigationMapRoute(null, mapView, mapboxMap,  com.mapbox.services.android.navigation.ui.v5.R.style.NavigationMapRoute)
                    }
                    navigationMapRoute!!.addRoute(currentRoute)
                }

                override fun onFailure(call: Call<DirectionsResponse>, throwable: Throwable) {}
            })
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent() {
        initializeLocationEngine()

        val locationComponentOptions = LocationComponentOptions.builder(this)
            .build()
        locationComponent = mapboxMap.locationComponent

        val locationComponentActivationOptions = LocationComponentActivationOptions
            .builder(this, mapboxMap.style!!)
            .locationEngine(arLocationEngine)
            .locationComponentOptions(locationComponentOptions)
            .build()

        locationComponent?.let {
            it.activateLocationComponent(locationComponentActivationOptions)
            it.isLocationComponentEnabled = true
            it.cameraMode = CameraMode.TRACKING
        }
    }

    @SuppressLint("MissingPermission")
    private fun initializeLocationEngine() {
        try {
            arLocationEngine.requestLocationUpdates(arLocationEngineRequest, locationCallback, mainLooper)
        } catch (se: SecurityException) {
            VisionLogger.d(TAG, se.toString())
        }

        arLocationEngine.getLastLocation(locationCallback)
    }
}