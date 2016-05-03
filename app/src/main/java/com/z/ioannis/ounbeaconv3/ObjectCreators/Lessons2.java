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

public class Lessons2 {

    private String LesID;
    private String Lname;
    private String[] toBcnos;
    private int nSlides;
    private String[] slides;
    private String[] imageURLs;

    public Lessons2(String LesID, String Lname, String[] toBcnos, int nSlides, String[] slides, String[] imageURLs) {
        this.LesID = LesID;
        this.Lname = Lname;
        this.toBcnos = toBcnos;
        this.nSlides = nSlides;
        this.slides = slides;
        this.imageURLs = imageURLs;
    }

    public Lessons2() {

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
}
