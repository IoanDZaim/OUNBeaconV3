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

import java.io.Serializable;

public class Lessons implements Serializable{

    private String LesID; //The ID of every Lesson. Unique of everyone
    private String Lname; //The title of the Lesson, used as menu title in the RoomsAdapter and as a footnote in the LessonsAdapter
    private String[] toBcnos; //No real use for it, used mostly for reference and testing
    private String[] slides; //The text of the cards that are being projected. Should be small
    private String[] imageURLs; //It has either null value, or an image URL. Must be the same size as slides array

    public Lessons(String LesID, String Lname, String[] toBcnos, String[] slides, String[] imageURLs) {
        this.LesID = LesID;
        this.Lname = Lname;
        this.toBcnos = toBcnos;
        this.slides = slides;
        this.imageURLs = imageURLs;
    }

    public Lessons() {

    }

    protected Lessons(Parcel in) {
        LesID = in.readString();
        Lname = in.readString();
        toBcnos = in.createStringArray();
        slides = in.createStringArray();
        imageURLs = in.createStringArray();
    }



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


}
