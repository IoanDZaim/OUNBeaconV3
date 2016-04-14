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
 * Created by Ioannis.D on 14-Mar-16.
 * ****************************************************************************
 */
package com.z.ioannis.ounbeaconv3.ObjectCreators;

import java.util.Arrays;

public class Rooms{

    public Rooms(String roomID, String BcName, String roomName, String welcMsg, int numOfLss, String[] lssTitles) {

        this.RoomID = roomID;
        this.BcName= BcName;
        this.RoomName = roomName;
        this.WelcMsg = welcMsg;
        this.NumOfLss = numOfLss;
        this.LssTitles = lssTitles;
    }

    public Rooms(){

    }
    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getBcName() {
        return BcName;
    }

    public void setBcName(String BcName) {
        this.BcName = BcName;
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
        WelcMsg = welcMsg;
    }

    public int getNumOfLss() {
        return NumOfLss;
    }

    public void setNumOfLss(int numOfLss) {
        NumOfLss = numOfLss;
    }

    public String[] getLssTitles() {
        return LssTitles;
    }

    public void setLssTitles(String[] lssTitles) {
        LssTitles = lssTitles;
    }

    private String RoomID;
    private String BcName;
    private String RoomName;
    private String WelcMsg;
    private int NumOfLss;
    private String[] LssTitles;

    @Override
    public String toString() {
        return "Rooms{" +
                "RoomID='" + RoomID + '\'' +
                ", BcName='" + BcName + '\'' +
                ", RoomName='" + RoomName + '\'' +
                ", WelcMsg='" + WelcMsg + '\'' +
                ", NumOfLss=" + NumOfLss +
                ", LssTitles=" + Arrays.toString(LssTitles) +
                '}';
    }
}
