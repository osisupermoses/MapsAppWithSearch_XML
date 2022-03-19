package com.osisupermoses.mapsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.osisupermoses.mapsapp.databinding.ActivityMapsBinding
import android.location.Geocoder


import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Transformations.map


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.ibLocationSearch.setOnClickListener {
            startActivity(Intent(this, MapsWithSearchActivity::class.java))
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val agbowo = LatLng(7.4453, 3.9139)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(agbowo).title("Marker in Agbowo"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(agbowo))

    /*    If you want to be more precise, say you want the map to show the street or town view,
           you will use the zoom values. They are usually between 1 and 20:
           1 - the world, 5 - landmass or continent, 10 - city, 15 - street, 20 - building.
     */

        // To see what street we have at 15:
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(agbowo, 15f))
    }

}