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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private CardScrollView mCardScroller;
    private BeaconManager beaconManager;
    private Region welten;
    private Beacon[] BconArray;
    private Intent intent;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = this;
        ArrayList<CardBuilder> card = new ArrayList<>();
        welten = new Region("Welten Region", null, null, null);
        beaconManager = new BeaconManager(getApplicationContext());
        final String jInfo = loadInfoJSON();

        String jBeacons = loadBeacons();
        String jRooms = loadRooms();
        String jLessons = loadLessons();

        new testLoader(jBeacons, jRooms, jLessons);

        card.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                .setText(R.string.Welcome)
                .setFootnote(R.string.WFootnote));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        intent = new Intent(this,RoomsActivity.class);

        mCardScroller = new CardScrollView(this);
        MainAdapter adapter1 = new MainAdapter(card, context);
        mCardScroller.setAdapter(adapter1);
        mCardScroller.activate();
        setContentView(mCardScroller);

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
                    int maj = BconArray[0].getMajor();
                    int min = BconArray[0].getMinor();
                    intent.putExtra("JSON", jInfo);
                    new jsonLoader(jInfo, maj, min);
                    beaconManager.stopRanging(welten);
                    startActivity(intent);
                }//if
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

    public String loadInfoJSON() {
        String json;
        try {
            InputStream is = getAssets().open("info3.json");
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
    }//loadInfoJSON

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
