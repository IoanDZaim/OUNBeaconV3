package com.z.ioannis.ounbeaconv3;

import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ioannis.D on 21-Mar-16.
 */
public class jsonLoader{

    private JSONObject jsonInfo;
    private Beacons bcon;
    private Rooms rooms;
    private Lessons lesson;
    private List<Beacons> BconsList = new ArrayList<>();
    private List<Rooms> RoomsList = new ArrayList<>();
    private List<Lessons> LessList = new ArrayList<>();
    private String[] SlidesTxts;
    private String[] TtlStrings;


    public jsonLoader (String jFile){
       try {
           jsonInfo = new JSONObject(jFile);
           JSONArray jsonArray = jsonInfo.optJSONArray("beacons");
           JSONArray jsonArray2 = jsonInfo.optJSONArray("rooms");
           JSONArray jsonArray3 = jsonInfo.optJSONArray("lessons");
           for (int i = 0; i < jsonArray.length(); i++) {
               bcon = new Beacons();
               JSONObject jsonObject = jsonArray.getJSONObject(i);

               String name = jsonObject.getString("name");
               bcon.setBName(name);

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
           }//for 1
           for (int i = 0; i < jsonArray2.length(); i++) {
               rooms = new Rooms();
               JSONObject jsonObject = jsonArray2.getJSONObject(i);

               String roomid = jsonObject.getString("RoomID");
               rooms.setRoomID(roomid);

               String bcname = jsonObject.getString("BcName");
               rooms.setBcName(bcname);

               String roomName = jsonObject.getString("RoomName");
               rooms.setRoomName(roomName);

               String wlcmsg = jsonObject.getString("WelcMsg");
               rooms.setWelcMsg(wlcmsg);

               int nlss = jsonObject.getInt("NumOfLss");
               rooms.setNumOfLss(nlss);

               TtlStrings = new String[nlss];
               for (int k = 0; k < nlss; k++) {
                   String ltittle = jsonObject.getString("LssTitle " + k);
                   TtlStrings[k] = ltittle;
               }
               rooms.setLssTitles(TtlStrings);

               RoomsList.add(rooms);
           }//for 2
           for (int i = 0; i < jsonArray3.length(); i++) {
               lesson = new Lessons();
               JSONObject jsonObject = jsonArray3.getJSONObject(i);

               int lnum = jsonObject.getInt("num");
               lesson.setLesNum(lnum);

               String lname = jsonObject.getString("name");
               lesson.setLname(lname);

               String rName = jsonObject.getString("roomname");
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
           }//for 3
       }catch (JSONException e) {
           e.printStackTrace();
       }
    }//jsonLoader

}
