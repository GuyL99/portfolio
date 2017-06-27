package com.example.dell.pokemonguy

import android.location.Location


class Pokemon {
    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var power:Double?=null

    var isCaught:Boolean?=null
    var location:Location?=null
    constructor(image:Int,name:String,des:String,power:Double,latitude:Double,longtitude:Double){
        this.name = name
        this.des = des
        this.power = power
        this.image = image
        this.location=Location(name)
        this.location!!.latitude=latitude
        this.location!!.longitude=longtitude
        this.isCaught= false


    }

}
