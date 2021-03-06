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

public class Rooms {

    public Rooms(String roomID, Beacons[] BcNames, String roomName, String WelcMsg) {

        this.RoomID = roomID;
        this.BcNames = BcNames;
        this.RoomName = roomName;
        this.WelcMsg = WelcMsg;
    }

    public Rooms(){

    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public Beacons[] getBcNames() {
        return BcNames;
    }

    public void setBcNames(Beacons[] BcName) {
        this.BcNames = BcName;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }


    public String getWelcMsg() {
        return WelcMsg;
    }

    public void setWelcMsg(String welcMsg) {
        this.WelcMsg = welcMsg;
    }

    private String RoomID; //Unique ID of the Room
    private Beacons[] BcNames; //Array of Beacons associated with this Room
    private String RoomName; //Name of the Room
    private String WelcMsg; //Welcome message that is projected when the user enters a Room and the Glass identifies it

}
