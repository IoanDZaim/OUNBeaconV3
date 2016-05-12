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
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Adapters.MainAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private CardScrollView mCardScroller;
    private BeaconManager beaconManager;
    private Region welten;
    private Context context;
    private MainAdapter adapter1;
    private Beacon[] BconArray;
    private Intent intent;
    private ArrayList<CardBuilder> card = new ArrayList<>();
    private List<Beacons> BCList;
    private int check1;
    private boolean check2 = false;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        check1 = 0;
        context = this;
        welten = new Region("Welten Region", null, null, null);
        beaconManager = new BeaconManager(getApplicationContext());

        String jBeacons = loadBeacons();
        String jRooms = loadRooms();
        String jLessons = loadLessons();

        new InfoLoader(jBeacons, jRooms, jLessons);

        card.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                .setText(R.string.Welcome)
                .setFootnote(R.string.WFootnote));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        intent = new Intent(this,RoomsActivity.class);

        mCardScroller = new CardScrollView(this);
        adapter1 = new MainAdapter(card, context);
        mCardScroller.setAdapter(adapter1);
        mCardScroller.activate();
        setContentView(mCardScroller);
        BCList = InfoLoader.getBconsList();

        beaconManager.setRangingListener(new BeaconManager.RangingListener(){
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                BconArray = new Beacon[list.size()];
                int j = 0;
                for (Beacon beacon : list){
                    BconArray[j]=beacon;
                    j++;
                }//insert found beacons on Array
                if ((BconArray.length )!= 0){
                    int maj = BconArray[0].getMajor();//might change this with the the mac method underneath
                    int min = BconArray[0].getMinor();
                    String mac = BconArray[0].getMacAddress().toString();
                    int flag=0;
                    for (Beacons beacons : BCList){
                        String check = beacons.getMac();
                        if(check.equals(mac)){
                            intent.putExtra("MAJ", maj);//change min and max with mac?
                            intent.putExtra("MIN", min);
                            intent.putExtra("Beacon", beacons);
                            beaconManager.stopRanging(welten);
                            startActivity(intent);
                            break;
                        }//if the beacon's mac exists, then start next Activity for current beacon data, else increase flag by 1
                        else{
                            flag++;
                        }//else
                        if((flag==BCList.size())&&(!check2))
                        {
                            card.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                                    .setText(R.string.Error )
                                    .setFootnote(R.string.WFootnote));
                            adapter1.notifyDataSetChanged();
                            mCardScroller.setSelection(1);
                            check2=true; //make message appear only once
                        }//if the flag variable has the same size as the List of beacons from the JSON file, then the beacon doesn't exist on the list, so display error message
                    }//run for all the beacons of the JSON file and check if discovered beacon exists in the list
                }//if there are any beacons on the array of the onBeaconsDiscovered, do the above, else display a NoBeacons message
                else {
                    check1++;
                    if (check1==20){
                        card.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                                .setText(R.string.NoBeacon)
                                .setFootnote(R.string.WFootnote));
                        adapter1.notifyDataSetChanged();
                        mCardScroller.setSelection(1);
                    }//display NoBeacons message
                }//else of the "if beacons exist" part
            }//onBeaconsDiscovered
            }//RangingListener
        );//ranginglistener
    }//onCreate

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(welten);
            }
        });
    }//onResumer

    @Override
    protected void onPause() {
        beaconManager.stopRanging(welten);
        mCardScroller.deactivate();
        super.onPause();
    }//onPause

    public String loadBeacons(){
        String json;
        try {
            InputStream is = getAssets().open("Beacons.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }//catch
        return json;
    }//loadBeacons

    public String loadRooms() {
        String json;
        try {
            InputStream is = getAssets().open("Rooms.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }//catch
        return json;
    }//loadRooms

    public String loadLessons(){
        String json;
        try {
            InputStream is = getAssets().open("Lessons.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }//catch
        return json;
    }//loadLessons


}//MainActivity
