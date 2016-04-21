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

public class Rooms2 {

    public Rooms2(String roomID, String[] BcNames, String roomName, int numOfLss, String[] rSlides) {

        this.RoomID = roomID;
        this.BcNames = BcNames;
        this.RoomName = roomName;
        this.NumOfLss = numOfLss;
        this.rSlides = rSlides;
    }

    public Rooms2(){

    }
    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String[] getBcNames() {
        return BcNames;
    }

    public void setBcNames(String[] BcName) {
        this.BcNames = BcName;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public int getNumOfLss() {
        return NumOfLss;
    }

    public void setNumOfLss(int numOfLss) {
        NumOfLss = numOfLss;
    }

    public String[] getrSlides() {
        return rSlides;
    }

    public void setrSlides(String[] rSlides) {
        this.rSlides = rSlides;
    }

    private String RoomID;
    private String[] BcNames;
    private String RoomName;
    private int NumOfLss;
    private String[] rSlides;

}
