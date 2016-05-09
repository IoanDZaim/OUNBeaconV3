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
 * Created by Ioannis.D on 21-Apr-16.
 * ****************************************************************************
 */
package com.z.ioannis.ounbeaconv3.ObjectCreators;

import android.os.Parcel;
import android.os.Parcelable;

public class Lessons implements Parcelable{

    private String LesID;
    private String Lname;
    private String[] toBcnos;
    private int nSlides;
    private String[] slides;
    private String[] imageURLs;

    public Lessons(String LesID, String Lname, String[] toBcnos, int nSlides, String[] slides, String[] imageURLs) {
        this.LesID = LesID;
        this.Lname = Lname;
        this.toBcnos = toBcnos;
        this.nSlides = nSlides;
        this.slides = slides;
        this.imageURLs = imageURLs;
    }

    public Lessons() {

    }

    protected Lessons(Parcel in) {
        LesID = in.readString();
        Lname = in.readString();
        toBcnos = in.createStringArray();
        nSlides = in.readInt();
        slides = in.createStringArray();
        imageURLs = in.createStringArray();
    }

    public static final Creator<Lessons> CREATOR = new Creator<Lessons>() {
        @Override
        public Lessons createFromParcel(Parcel in) {
            return new Lessons(in);
        }

        @Override
        public Lessons[] newArray(int size) {
            return new Lessons[size];
        }
    };

    public String getLesID() {
        return LesID;
    }

    public void setLesID(String lesID) {
        LesID = lesID;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String[] getToBcnos() {
        return toBcnos;
    }

    public void setToBcnos(String[] toBcnos) {
        this.toBcnos = toBcnos;
    }

    public int getnSlides() {
        return nSlides;
    }

    public void setnSlides(int nSlides) {
        this.nSlides = nSlides;
    }

    public String[] getSlides() {
        return slides;
    }

    public void setSlides(String[] slides) {
        this.slides = slides;
    }

    public String[] getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(String[] imageURLs) {
        this.imageURLs = imageURLs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(LesID);
        dest.writeString(Lname);
        dest.writeStringArray(toBcnos);
        dest.writeInt(nSlides);
        dest.writeStringArray(slides);
        dest.writeStringArray(imageURLs);
    }
}
