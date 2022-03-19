package com.osisupermoses.mapsapp

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.osisupermoses.mapsapp.databinding.ActivityMapsWithSearchBinding
import java.io.IOException

class MapsWithSearchActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapsWithSearchBinding
    private lateinit var mMap: GoogleMap

    // creating a variable
    // for search view.
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsWithSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initializing our search view.
        searchView = binding.idSearchView

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapWithSearch) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // adding on query listener for our search view.
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are getting the
                // location name from search view.
                val location: String = searchView!!.query.toString()

                // below line is to create a list of address
                // where we will store the list of all address.
                var addressList: List<Address>? = null

                // checking if the entered location is null or not.
                // on below line we are creating and initializing a geo coder.
                val geocoder = Geocoder(this@MapsWithSearchActivity)
                try {
                    // on below line we are getting location from the
                    // location name and adding that location to address list.
                    addressList = geocoder.getFromLocationName(location, 1)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                // on below line we are getting the location
                // from our list a first position.
                val address: Address = addressList!![0]

                // on below line we are creating a variable for our location
                // where we will add our locations latitude and longitude.
                val latLng = LatLng(address.latitude, address.longitude)

                // on below line we are adding marker to that position.
                mMap.addMarker(MarkerOptions().position(latLng).title(location))

                // below line is to animate camera to that position.
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        // at last we calling our map fragment to update.
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
}