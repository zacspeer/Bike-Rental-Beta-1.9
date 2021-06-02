package com.example.bikerentalbeta1_8;

public class Data {
    public double lat, lang;
    public  String vehno, name;
    Data(){

    }

    Data(double lat,double lang ,String name , String vehno){
        this.lat = lat;
        this.lang = lang;
        this.name = name;
        this.vehno = vehno;
    }
}
