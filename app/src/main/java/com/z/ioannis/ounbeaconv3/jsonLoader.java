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
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class jsonLoader{

    private static Beacons closestBeacon;
    private static Rooms currentRoom;


    public jsonLoader (String jFile, int maj, int min){
        JSONObject jsonInfo;
        Beacons bcon;
        Rooms rooms;
        String[] rSlides;

       try {
           jsonInfo = new JSONObject(jFile);
           JSONArray jsonArray = jsonInfo.optJSONArray("beacons");
           JSONArray jsonArray2 = jsonInfo.optJSONArray("rooms");
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
       }catch (JSONException e) {
           e.printStackTrace();
       }
    }//jsonLoader


    public static Beacons getClosestBeacon() {
        return closestBeacon;
    }
    public static Rooms getCurrentRoom() {
        return currentRoom;
    }
}
