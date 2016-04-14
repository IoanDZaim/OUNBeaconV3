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
 * Created by Ioannis.D on 21-Mar-16.
 * ****************************************************************************
 */
package com.z.ioannis.ounbeaconv3;

import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jsonLoader{

    private JSONObject jsonInfo;
    private Beacons bcon;
    private Rooms rooms;
    private Lessons lesson;
    private Beacons closestBeacon;
    private static Rooms currentRoom;
    private static List<Lessons> LessList = new ArrayList<>();
    private String[] SlidesTxts;
    private String[] rSlides;


    public jsonLoader (String jFile, int maj, int min){
       try {
           jsonInfo = new JSONObject(jFile);
           JSONArray jsonArray = jsonInfo.optJSONArray("beacons");
           JSONArray jsonArray2 = jsonInfo.optJSONArray("rooms");
           JSONArray jsonArray3 = jsonInfo.optJSONArray("lessons");
           for (int i = 0; i < jsonArray.length(); i++) {
               JSONObject jsonObject = jsonArray.getJSONObject(i);
               int Major = jsonObject.getInt("Major");
               int Minor = jsonObject.getInt("Minor");
               if ((Major == maj)&&(Minor == min)){
                   bcon = new Beacons();
                   String name = jsonObject.getString("name");
                   bcon.setBName(name);
                   String uuid = jsonObject.getString("uuid");
                   bcon.setUuid(uuid);
                   bcon.setMajor(Major);
                   bcon.setMinor(Minor);
                   String Mac = jsonObject.getString("Mac");
                   bcon.setMac(Mac);
                   String Colour = jsonObject.getString("Colour");
                   bcon.setColour(Colour);
                   closestBeacon = bcon;
               }
           }//for 1
           for (int i = 0; i < jsonArray2.length(); i++) {
               JSONObject jsonObject = jsonArray2.getJSONObject(i);
               String bcname = jsonObject.getString("BcName");
               if (bcname.equals(closestBeacon.getBName())){
                   rooms = new Rooms();
                   String roomid = jsonObject.getString("RoomID");
                   rooms.setRoomID(roomid);
                   rooms.setBcName(bcname);
                   String roomName = jsonObject.getString("RoomName");
                   rooms.setRoomName(roomName);
                   int nOSlds = jsonObject.getInt("NumOfSlides");
                   rooms.setNumOfLss(nOSlds);
                   rSlides = new String[nOSlds];
                   for (int k = 0; k < nOSlds; k++) {
                       String rslide = jsonObject.getString("Slide " + k);
                       rSlides[k] = rslide;
                   }
                   rooms.setrSlides(rSlides);
                   currentRoom = rooms;
               }
           }//for 2
               for (int i = 0; i < jsonArray3.length(); i++) {
                   JSONObject jsonObject = jsonArray3.getJSONObject(i);
                   String rName = jsonObject.getString("roomname");
                   if (rName.equals(currentRoom.getRoomName())){
                       lesson = new Lessons();
                       int lnum = jsonObject.getInt("num");
                       lesson.setLesNum(lnum);
                       String lname = jsonObject.getString("name");
                       lesson.setLname(lname);
                       lesson.setRoomName(rName);
                       int nSlides = jsonObject.getInt("NumOfSlides");
                       lesson.setnSlides(nSlides);
                       SlidesTxts = new String[nSlides];
                       for (int k = 0; k < nSlides; k++) {
                           String slide = jsonObject.getString("Slide " + k);
                           SlidesTxts[k] = slide;
                       }//for in for3
                       lesson.setSlides(SlidesTxts);
                       LessList.add(lesson);
                   }
               }//for 3
       }catch (JSONException e) {
           e.printStackTrace();
       }
    }//jsonLoader


    public static List<Lessons> getLessList() {
        return LessList;
    }
    public static void clearLessList(){
        LessList.clear();
    }
    public Beacons getClosestBeacon() {
        return closestBeacon;
    }
    public static Rooms getCurrentRoom() {
        return currentRoom;
    }
}
