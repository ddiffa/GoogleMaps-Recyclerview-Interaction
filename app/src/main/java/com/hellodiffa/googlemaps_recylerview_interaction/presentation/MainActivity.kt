package com.hellodiffa.googlemaps_recylerview_interaction.presentation

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.ui.IconGenerator
import com.hellodiffa.googlemaps_recylerview_interaction.R
import com.hellodiffa.googlemaps_recylerview_interaction.common.HorizontalRecyclerViewScrollListener
import com.hellodiffa.googlemaps_recylerview_interaction.model.Hotel
import com.hellodiffa.googlemaps_recylerview_interaction.utils.getData
import com.hellodiffa.googlemaps_recylerview_interaction.utils.getFormat
import kotlinx.android.synthetic.main.activity_main.*

/*
* CREATED By Diffa
* linkedin : www.linkedin.com/in/diffadwi
* github : www.github.com/ddiffa
* email : ddiffa2@gmail.com
*/

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mAdapter: MainAdapter
    private lateinit var mGoogleMap: GoogleMap
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = MainAdapter()
        layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rv_maps.layoutManager = layoutManager

        rv_maps.itemAnimator = DefaultItemAnimator()
        rv_maps.setPadding(8, 0, 8, 0)
        rv_maps.adapter = mAdapter
        rv_maps.addOnScrollListener(HorizontalRecyclerViewScrollListener(this::onScrolled))
        rv_maps.setHasFixedSize(true)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.mGoogleMap = googleMap

        mGoogleMap.setOnMarkerClickListener(this::onMarkerClick)

        Handler().postDelayed(
            Runnable {
                setData()
            }, 500
        )
    }

    private fun setData() {
        mAdapter.setData(getData())
        val iconFactory = IconGenerator(this)
        for (data in getData()) {
            iconFactory.setColor(resources.getColor(R.color.colorWhite))
            addIcon(iconFactory, getFormat().format(data.price), LatLng(data.Lat, data.Lang))
        }
        setCamera(getData().first())
    }


    private fun addIcon(iconFactory: IconGenerator, text: CharSequence, position: LatLng) {
        val markerOptions =
            MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text)))
                .position(position)
                .anchor(iconFactory.anchorU, iconFactory.anchorV)
        mGoogleMap.addMarker(markerOptions)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        var s = marker.id
        s = s.replace("m", "")
        rv_maps.scrollToPosition(s.toInt())
        return true
    }


    private fun onScrolled(position: Int) {
        setCamera(getData()[position])
        mGoogleMap.setOnCameraMoveListener {
            rv_maps.smoothScrollToPosition(position)
        }
    }

    private fun setCamera(data: Hotel) {
        val bottomBoundary = data.Lat - .1
        val leftBoundary = data.Lang - .1
        val topBoundary = data.Lat + .1
        val rightBoundary = data.Lang + .1
        val mMapBoundary = LatLngBounds(
            LatLng(bottomBoundary, leftBoundary),
            LatLng(topBoundary, rightBoundary)
        )
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0))

    }


}
