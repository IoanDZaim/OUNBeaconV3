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
 * ****************************************************************************
 */
package com.z.ioannis.ounbeaconv3;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;
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
    private CardScrollView mCardScroller1;
    private BeaconManager beaconManager;
    private Region welten;
    private Beacon[] BconArray;
    private Beacons bcon;
    private Rooms rooms;
    private Lessons lesson;
    private List<Beacons> BconsList = new ArrayList<>();
    private List<Rooms> RoomsList = new ArrayList<>();
    private List<Lessons> LessList = new ArrayList<>();
    private JSONObject jsonInfo;
    private Context context;
    private String[] SlidesTxts;
    private String[] TtlStrings;
    private String ClosestBconName;
    private ArrayList<CardBuilder> cards;
    private ArrayList<CardBuilder> cards2;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        context=this;
        cards = new ArrayList<>();
        cards2 = new ArrayList<>();
        welten = new Region("Welten Region", null, null, null);
        beaconManager = new BeaconManager(getApplicationContext());

        cards2.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                .setText(R.string.Welcome)
                .setFootnote(R.string.WFootnote));


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
                    for (int k = 0; k < nlss; k++){
                        String ltittle = jsonObject.getString("LssTitle "+k);
                        TtlStrings[k]=ltittle;
                    }
                    rooms.setLssTitles(TtlStrings);

                    RoomsList.add(rooms);
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

                    SlidesTxts = new String[nSlides];
                    for (int k = 0; k < nSlides; k++){
                        String slide = jsonObject.getString("Slide " + k);
                        SlidesTxts[k]=slide;
                    }//for in for3
                    lesson.setSlides(SlidesTxts);
                    LessList.add(lesson);
                }//for 3
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mCardScroller1 = new CardScrollView(this);
        CreatedCardsAdapter adapter1 = new CreatedCardsAdapter(cards2, context);
        mCardScroller1.setAdapter(adapter1);
        mCardScroller1.activate();
        setContentView(mCardScroller1);

        beaconManager.setRangingListener(new BeaconManager.RangingListener(){
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                BconArray = new Beacon[list.size()];
                int j = 0;
                for (Beacon beacon : list){
                    BconArray[j]=beacon;
                    j++;
                }
                PrepareCards();
                mCardScroller1.deactivate();
                mCardScroller = new CardScrollView(MainActivity.this);
                CreatedCardsAdapter adapter = new CreatedCardsAdapter(cards, context);
                mCardScroller.setAdapter(adapter);
                mCardScroller.activate();
                mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Plays disallowed sound to indicate that TAP actions are not supported.
                        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                        am.playSoundEffect(Sounds.TAP);;
                        mCardScroller1.activate();
                        setContentView(mCardScroller1);
                    }
                });
                setContentView(mCardScroller);//*/
            }
            }
        );//ranginglistener


/**
        // Handle the TAP event.
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Plays disallowed sound to indicate that TAP actions are not supported.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(Sounds.DISALLOWED);
            }
        });

//*/

    }

    private void PrepareCards(){

        beaconManager.stopRanging(welten);
        Iterator<Rooms> itr = RoomsList.iterator();
        Iterator<Beacons> itr2 = BconsList.iterator();
        Iterator<Lessons> itr3 = LessList.iterator();
        int maj = BconArray[0].getMajor();
        int min = BconArray[0].getMinor();
        while (itr2.hasNext()) {
            Beacons check = itr2.next();
            int majcheck = check.getMajor();
            int mincheck = check.getMinor();
            if ((majcheck == maj) && (mincheck == min)) {
                ClosestBconName = check.getBName();
                break;
            }//if
        }//while

        while (itr.hasNext()) {
            Rooms check = itr.next();
            String RBname = check.getBcName();
            if (RBname.equals(ClosestBconName)) {
                String[] LessonTitles;
                LessonTitles = check.getLssTitles();
                String cardText;
                cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                    .setText(check.getWelcMsg())
                    .setFootnote(check.getRoomName()));
                for (int i = 0; i < check.getNumOfLss(); i++) {
                    cardText = LessonTitles[i];
                    cards.add(new CardBuilder(context, CardBuilder.Layout.MENU)
                        .setText(cardText)
                        .setFootnote(check.getRoomName()));
                }//for
            }//if
        }//2nd while
        while (itr3.hasNext()){
            Lessons check = itr3.next();
            String[] LessonSlides=check.getSlides();
            String CardText;
            for (int i =0; i < check.getnSlides(); i++){
                CardText = LessonSlides[i];
                cards2.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                    .setText(CardText)
                    .setFootnote(check.getRoomName()+ ": " +check.getLname())
                );
            }
        }
    }//PrepareCards

    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(welten);
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(welten);
        super.onPause();
    }

    public String loadInfoJSON() {
        String json;
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
