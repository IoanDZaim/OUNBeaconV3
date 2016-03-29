package com.z.ioannis.ounbeaconv3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Cards.CardCreator;
import com.z.ioannis.ounbeaconv3.Cards.CreatedCardsAdapter;
import com.z.ioannis.ounbeaconv3.Classes.Beacons;
import com.z.ioannis.ounbeaconv3.Classes.Lessons;
import com.z.ioannis.ounbeaconv3.Classes.Rooms;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity {


    private CardScrollView mCardScroller;
    private BeaconManager beaconManager;
    private Region welten;
    private Beacon[] BconArray;
    private Beacons bcon;
    private Rooms rooms;
    private Lessons lesson;
    private List<Beacons> lista = new ArrayList<>();
    private List<Rooms> listb = new ArrayList<>();
    private List<Lessons> listc = new ArrayList<>();
    private JSONObject jsonInfo;
    private List<CardCreator> mCards;
    private Context context;
    private String[] temp;
    private String[] temp1;
    private String BeacName;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        context=this;

        welten = new Region("Welten Region", null, null, null);
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setRangingListener(new BeaconManager.RangingListener(){
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                BconArray = new Beacon[list.size()];
                int j = 0;
                for (Beacon beacon : list){
                    BconArray[j]=beacon;
                    j++;
                }
//*/
                try {
                    if (jsonInfo ==null) {
                        jsonInfo = new JSONObject(loadInfoJSON());
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

                            lista.add(bcon);
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

                            temp1 = new String[nlss];
                            for (int k = 0; k < nlss; k++){
                                String ltittle = jsonObject.getString("LssTitle "+k);
                                temp1[k]=ltittle;
                            }
                            rooms.setLssTitles(temp1);

                            listb.add(rooms);
                        }//for 2
                        for (int i = 0; i < jsonArray3.length(); i++){
                            lesson = new Lessons();
                            JSONObject jsonObject = jsonArray3.getJSONObject(i);

                            String lname = jsonObject.getString("name");
                            lesson.setLname(lname);

                            String rName = jsonObject.getString("roomname");
                            lesson.setRoomName(rName);

                            int nSlides = jsonObject.getInt("NumOfSlides");
                            lesson.setnSlides(nSlides);

                            temp = new String[nSlides];
                            for (int k = 0; k < nSlides; k++){
                                String slide = jsonObject.getString("Slide " + k);
                                temp[k]=slide;
                            }//for in for3
                            lesson.setSlides(temp);
                            listc.add(lesson);
                        }//for 3
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PrepareCards();
                mCardScroller = new CardScrollView(MainActivity.this);
                CreatedCardsAdapter adapter = new CreatedCardsAdapter(mCards, context);
                mCardScroller.setAdapter(adapter);
                mCardScroller.activate();
                setContentView(mCardScroller);//*/

            }



            }
        );//ranginglistener



        //PrepareCards();
    /**    mCardScroller = new CardScrollView(this);
        CreatedCardsAdapter adapter = new CreatedCardsAdapter(mCards, context);
        mCardScroller.setAdapter(adapter);
        mCardScroller.activate();

        // Handle the TAP event.
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Plays disallowed sound to indicate that TAP actions are not supported.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(Sounds.DISALLOWED);
            }
        });*/
       // setContentView(mCardScroller);
//*/
    }

    private void PrepareCards(){

        beaconManager.stopRanging(welten);
        mCards = new ArrayList<>();
        Iterator<Rooms> itr = listb.iterator();
        Iterator<Beacons> itr2 = lista.iterator();
        CardCreator cc;

        int maj = BconArray[0].getMajor();
        int min = BconArray[0].getMinor();
        while (itr2.hasNext()) {
            Beacons check = itr2.next();
            int majcheck = check.getMajor();
            int mincheck = check.getMinor();
            if ((majcheck == maj) && (mincheck == min)) {
                BeacName= check.getBName();
                break;
            }//if
        }//while

        while (itr.hasNext()) {
            Rooms check = itr.next();
            String RBname = check.getBcName();
            if (RBname.equals(BeacName)) {
                String[] keimeno;
                keimeno = check.getLssTitles();
                String babis;
                cc = new CardCreator(check.getWelcMsg(), check.getRoomID());
                mCards.add(cc);
                for (int i = 0; i < check.getNumOfLss(); i++) {
                    babis = keimeno[i];
                    cc = new CardCreator(babis, check.getRoomName());
                    mCards.add(cc);
                }
            } else{
                cc = new CardCreator((R.string.No_match+BeacName),BeacName);
                mCards.add(cc);
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(welten);
            }
        });  //*/
      //  mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(welten);
       // mCardScroller.deactivate();
        super.onPause();
    }

    public String loadInfoJSON() {
        String json = null;
        try {
            InputStream is = getAssets().open("Info2.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
