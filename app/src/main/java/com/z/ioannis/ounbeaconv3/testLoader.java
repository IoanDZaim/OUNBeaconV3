package com.z.ioannis.ounbeaconv3;

import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms2;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by Ioannis.D on 21-Apr-16.
 */
public class testLoader {

    public testLoader (String jFile){
        JSONObject jInfo;
        try {
            jInfo = new JSONObject(jFile);
            JSONArray jsonArray = jInfo.optJSONArray("rooms");
            for (int i=0; i<jsonArray.length();i++){
                Rooms2 room = new Rooms2();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
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
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
