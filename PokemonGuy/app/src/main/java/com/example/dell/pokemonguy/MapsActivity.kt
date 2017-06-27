package com.example.dell.pokemonguy

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkPerm()
        loadPokemon()
    }

    var AccesLocation = 123
    fun checkPerm(){

        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),AccesLocation)
                return
            }
        }
        getLocation()
    }

    fun getLocation(){
        Toast.makeText(this,"user location acces on",Toast.LENGTH_LONG).show()

        var myLocation = MyLocationListner()
        var locationManagar = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //it's ok
        locationManagar.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)
        var myThread= myThread()
        myThread.start()
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
        mMap.addMarker(MarkerOptions().position(sydney).title("me").snippet("here is my location").icon(BitmapDescriptorFactory.fromResource(R.drawable.Ash)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,20f))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            AccesLocation->{

                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getLocation()
                }else{
                    Toast.makeText(this,"we cannot acces your location",Toast.LENGTH_LONG).show()
                }

            }


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    var location:Location?=null
    inner class MyLocationListner:LocationListener{


        constructor(){
            location=Location("Start")
            location!!.longitude=0.0
            location!!.latitude=0.0 //!!!!!******

        }
        override fun onLocationChanged(p0: Location?) {

            location= p0
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    var oldLocation:Location?=null

    inner class myThread:Thread{
        constructor():super(){
            oldLocation = Location("Start")
            oldLocation!!.latitude=0.0
            oldLocation!!.longitude=0.0
        }

        override  fun run(){
            while (true){
                try {
                    if(oldLocation!!.distanceTo(location)==0f){
                        continue
                    }


                    oldLocation=location
                    runOnUiThread() {
                        mMap!!.clear()
                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        mMap.addMarker(MarkerOptions().position(sydney).title("me").snippet("here is my location").icon(BitmapDescriptorFactory.fromResource(R.drawable.Ash)))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20f))

                        for(i in 0..listOfPokemons.size-1){
                            var newPokemon  =listOfPokemons[i]
                            if(newPokemon.isCaught==false){
                                val pokemon = LatLng(newPokemon.location!!.latitude, newPokemon.location!!.longitude)
                                mMap.addMarker(MarkerOptions().position(pokemon).title(newPokemon.name).snippet(newPokemon.des+" power:"+newPokemon.power).icon(BitmapDescriptorFactory.fromResource(newPokemon.image!!)))
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20f))



                                if(location!!.distanceTo(newPokemon.location)<2){
                                    newPokemon.isCaught=true
                                    listOfPokemons[i]=newPokemon
                                    playerPower+=newPokemon.power!!
                                    Toast.makeText(applicationContext,"You cought a new pokemon grats your new power is $playerPower",Toast.LENGTH_LONG).show()
                                }


                            }

                        }
                    }
                    Thread.sleep(1000)
                    } catch (ex:Exception){

                    }

            }
        }
    }


    var playerPower=0.0
    var listOfPokemons=ArrayList<Pokemon>()

    fun loadPokemon(){

        listOfPokemons.add(Pokemon((R.drawable.charmander),"Charmander","fire pokemon", 55.0,37.77899,-122.4018))
        listOfPokemons.add(Pokemon((R.drawable.Pikachu),"Pikachu","electric pokemon", 45.0,37.8,-122.4))
        listOfPokemons.add(Pokemon((R.drawable.balbsour),"Balbasour","leaf pokemon", 90.5,37.7949,-122.41))
        listOfPokemons.add(Pokemon((R.drawable.sqirtle),"Charmander","water pokemon", 33.5,37.78166,-122.412))
    }

}
