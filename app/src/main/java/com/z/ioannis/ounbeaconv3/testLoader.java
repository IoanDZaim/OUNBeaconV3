package com.z.ioannis.ounbeaconv3;

import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons2;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms2;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.List;

/**
 * Created by Ioannis.D on 21-Apr-16.
 */
public class testLoader {

    private List<Lessons2> LssList;
    private List<Rooms2> RoomsList;
    private List<Beacons> BconsList;

    public testLoader (String jBcons, String jRooms, String jLss){
        JSONObject jInfo1;
        JSONObject jInfo2;
        JSONObject jInfo3;
        try {
            jInfo1 = new JSONObject(jBcons);
            jInfo2 = new JSONObject(jRooms);
            jInfo3 = new JSONObject(jLss);
            JSONArray jsonArray1 = jInfo1.optJSONArray("beacons");
            JSONArray jsonArray2 = jInfo2.optJSONArray("rooms");
            JSONArray jsonArray3 = jInfo3.optJSONArray("lessons");

            for (int i=0; i<jsonArray1.length(); i++){
                Beacons bcon = new Beacons();
                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                String name = jsonObject.getString("name");
                bcon.setBName(name);
                String rName = jsonObject.getString("Room");
                bcon.setRoomname(rName);
                String uuid = jsonObject.getString("uuid");
                bcon.setUuid(uuid);
                int Major = jsonObject.getInt("Major");
                bcon.setMajor(Major);
                int Minor = jsonObject.getInt("Minor");
                bcon.setMinor(Minor);
                String Mac = jsonObject.getString("Mac");
                bcon.setMac(Mac);
                String Colour = jsonObject.getString("Colour");
                bcon.setColour(Colour);
                BconsList.add(bcon);
            }//for beacons

            for (int i=0; i<jsonArray2.length();i++){
                Rooms2 room = new Rooms2();
                JSONObject jsonObject = jsonArray2.getJSONObject(i);
                String roomid = jsonObject.getString("RoomID");
                room.setRoomID(roomid);
                String roomName = jsonObject.getString("RoomName");
                room.setRoomName(roomName);
                JSONArray bcons = jsonObject.getJSONArray("Beacons");
                String[] bc = new String[bcons.length()];
                for (int k=0; k<bcons.length();k++){
                    bc[k] = bcons.getString(k);
                }
                room.setBcNames(bc);
                int nOSlds = jsonObject.getInt("NumOfSlides");
                room.setNumOfLss(nOSlds);
                JSONArray slides = jsonObject.getJSONArray("Slides");
                String[] sli = new String[slides.length()];
                for (int j=0; j<slides.length();j++){
                    sli[j] = slides.getString(j);
                }
                room.setrSlides(sli);
                RoomsList.add(room);
            }//for rooms

            for (int i= 0; i<jsonArray3.length(); i++){
                Lessons2 lesson = new Lessons2();
                JSONObject jsonObject = jsonArray3.getJSONObject(i);
                int lssID = jsonObject.getInt("num");
                lesson.setLesNum(lssID);
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
                LssList.add(lesson);
            }//for lessons

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<Lessons2> getLssList() {
        return LssList;
    }

    public List<Rooms2> getRoomsList() {
        return RoomsList;
    }

    public List<Beacons> getBconsList() {
        return BconsList;
    }
}