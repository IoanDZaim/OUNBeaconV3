package com.z.ioannis.ounbeaconv3.Classes;


/**
 * Created by Ioannis.D on 03-Mar-16.
 */

public class Beacons {
    @Override
    public String toString() {
        return "Beacons{" +
                "BName='" + BName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", Major=" + Major +
                ", Minor=" + Minor +
                ", Mac='" + Mac + '\'' +
                ", Colour='" + Colour + '\'' +
                '}';
    }

    private String BName;
    private String uuid;
    private int Major;
    private int Minor;
    private String Mac;
    private String Colour;

    public String getBName() {
        return BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getMajor() {
        return Major;
    }

    public void setMajor(int major) {
        Major = major;
    }

    public int getMinor() {
        return Minor;
    }

    public void setMinor(int minor) {
        Minor = minor;
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }



    public Beacons(String BName, String uuid, int major, int minor, String mac, String colour) {
        this.BName = BName;
        this.uuid = uuid;
        this.Major = major;
        this.Minor = minor;
        this.Mac = mac;
        this.Colour = colour;
    }


    public Beacons() {

    }

}

