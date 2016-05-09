/**
 * ****************************************************************************
 * Copyright (C) 2016 Open Universiteit Nederland
 *
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors: Ioannis D. Zaimidis
 * Created by Ioannis.D on 22-Apr-16.
 * ****************************************************************************
 */

package com.z.ioannis.ounbeaconv3.ObjectCreators;

import android.os.Parcel;
import android.os.Parcelable;

public class Beacons implements Parcelable {

    private String BName;
    private String uuid;
    private int Major;
    private int Minor;
    private String Mac;
    private String Colour;
    private Lessons[] LssList;

    protected Beacons(Parcel in) {
        BName = in.readString();
        uuid = in.readString();
        Major = in.readInt();
        Minor = in.readInt();
        Mac = in.readString();
        Colour = in.readString();
    }

    public static final Creator<Beacons> CREATOR = new Creator<Beacons>() {
        @Override
        public Beacons createFromParcel(Parcel in) {
            return new Beacons(in);
        }

        @Override
        public Beacons[] newArray(int size) {
            return new Beacons[size];
        }
    };

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

    public Lessons[] getLssList() {
        return LssList;
    }

    public void setLssList(Lessons[] lssList) {
        LssList = lssList;
    }

    public Beacons(String BName, String uuid, int major, int minor, String mac, String colour, Lessons[] LssList) {
        this.BName = BName;
        this.uuid = uuid;
        this.Major = major;
        this.Minor = minor;
        this.Mac = mac;
        this.Colour = colour;
        this.LssList = LssList;
    }


    public Beacons() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(BName);
        dest.writeString(uuid);
        dest.writeInt(Major);
        dest.writeInt(Minor);
        dest.writeString(Mac);
        dest.writeString(Colour);
    }
}
