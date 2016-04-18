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
 * Created by Ioannis.D on 22-Mar-16.
 * ****************************************************************************
 */
package com.z.ioannis.ounbeaconv3.ObjectCreators;

public class Lessons {

    private int LesNum;
    private String Lname;
    private String roomName;
    private int nSlides;
    private String[] slides;

    public Lessons(int lesNum, String lname, String roomName, int nSlides, String[] slides) {
        LesNum = lesNum;
        Lname = lname;
        this.roomName = roomName;
        this.nSlides = nSlides;
        this.slides = slides;
    }

    public Lessons() {

    }


    public int getLesNum() {
        return LesNum;
    }

    public void setLesNum(int lesNum) {
        LesNum = lesNum;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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


}
