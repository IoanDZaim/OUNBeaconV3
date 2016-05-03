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
package com.z.ioannis.ounbeaconv3;

import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons2;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons2;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms2;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfoLoader {

    private static List<Lessons2> LssList = new ArrayList<>();
    private static List<Rooms2> RoomsList = new ArrayList<>();
    private static List<Beacons2> BconsList = new ArrayList<>();


    public InfoLoader(String jBcons, String jRooms, String jLss){
        JSONObject beaconsJInfo;
        JSONObject roomsJInfo;
        JSONObject lessonsJInfo;
        try {
            beaconsJInfo = new JSONObject(jBcons);
            roomsJInfo = new JSONObject(jRooms);
            lessonsJInfo = new JSONObject(jLss);
            JSONArray jBconsArray = beaconsJInfo.optJSONArray("beacons");
            JSONArray jRoomsArray = roomsJInfo.optJSONArray("rooms");
            JSONArray jLssArray = lessonsJInfo.optJSONArray("lessons");

            for (int i= 0; i<jLssArray.length(); i++){
                Lessons2 lesson = new Lessons2();
                JSONObject jsonObject = jLssArray.getJSONObject(i);
                String lssID = jsonObject.getString("id");
                lesson.setLesID(lssID);
                String name = jsonObject.getString("name");
                lesson.setLname(name);
                JSONArray bcons = jsonObject.getJSONArray("toBcons");
                String[] bc = new String[bcons.length()];
                for (int j=0; j<bcons.length();j++){
                    bc[j] = bcons.getString(j);
                }
                lesson.setToBcnos(bc);
                int nSld = jsonObject.getInt("NumOfSlides");
                lesson.setnSlides(nSld);
                JSONArray slides = jsonObject.getJSONArray("Slides");
                String[] sli = new String[slides.length()];
                for (int k=0; k<slides.length();k++){
                    sli[k] = slides.getString(k);
                }
                lesson.setSlides(sli);
                JSONArray images = jsonObject.getJSONArray("Images");
                String[] url = new String[images.length()];
                for (int l=0 ; l<images.length(); l++){
                    url[l] = images.getString(l);
                }
                lesson.setImageURLs(url);
                LssList.add(lesson);
            }//for lessons

            for (int i=0; i<jBconsArray.length(); i++){
                Beacons2 beacon = new Beacons2();
                JSONObject jsonObject = jBconsArray.getJSONObject(i);
                String name = jsonObject.getString("Name");
                beacon.setBName(name);
                String uuid = jsonObject.getString("Uuid");
                beacon.setUuid(uuid);
                int Major = jsonObject.getInt("Major");
                beacon.setMajor(Major);
                int Minor = jsonObject.getInt("Minor");
                beacon.setMinor(Minor);
                String Mac = jsonObject.getString("Mac");
                beacon.setMac(Mac);
                String Colour = jsonObject.getString("Colour");
                beacon.setColour(Colour);
                JSONArray lss = jsonObject.getJSONArray("Lessons");
                Lessons2[] Les = new Lessons2[lss.length()];
                for (int j=0; j<lss.length();j++ ){
                    for (Lessons2 lesson : LssList){
                        String lsn = lss.getString(j);
                        if (lesson.getLesID().equals(lsn)){
                            Les[j]=lesson;
                        }//if
                    }// for each
                }//for
                beacon.setLssList(Les);
                BconsList.add(beacon);
            }//for beacons

            for (int i=0; i<jRoomsArray.length();i++){
                Rooms2 room = new Rooms2();
                JSONObject jsonObject = jRoomsArray.getJSONObject(i);
                String roomid = jsonObject.getString("RoomID");
                room.setRoomID(roomid);
                String roomName = jsonObject.getString("RoomName");
                room.setRoomName(roomName);
                JSONArray bcons = jsonObject.getJSONArray("Beacons");
                Beacons2[] bc = new Beacons2[bcons.length()];
                for (int k=0; k<bcons.length();k++){
                    for (Beacons2 beacon : BconsList){
                        String bcn = bcons.getString(k);
                        if (beacon.getBName().equals(bcn)){
                            bc[k] = beacon;
                        }//if
                    }//for each
                }//for
                room.setBcNames(bc);
                String Msg = jsonObject.getString("WelcomeMsg");
                room.setWelcMsg(Msg);
                RoomsList.add(room);
            }//for rooms


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static List<Lessons2> getLssList() {
        return LssList;
    }

    public static List<Rooms2> getRoomsList() {
        return RoomsList;
    }

    public static List<Beacons2> getBconsList() {
        return BconsList;
    }
}
