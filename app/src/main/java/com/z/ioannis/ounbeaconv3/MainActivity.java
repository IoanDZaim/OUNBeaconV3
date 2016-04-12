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
import com.z.ioannis.ounbeaconv3.Adapters.CreatedCardsAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity {


    private CardScrollView mCardScroller1;
    private BeaconManager beaconManager;
    private Region welten;
    private Beacon[] BconArray;
    private List<Lessons> LessList = new ArrayList<>();
    private Context context;
    private ArrayList<CardBuilder> cards;
    private ArrayList<CardBuilder> cards2;
    private int cPosition;
    private Intent intent;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        context = this;
        cards = new ArrayList<>();
        cards2 = new ArrayList<>();
        welten = new Region("Welten Region", null, null, null);
        beaconManager = new BeaconManager(getApplicationContext());

        cards2.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                .setText(R.string.Welcome)
                .setFootnote(R.string.WFootnote));


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        intent = new Intent(this,RoomsActivity.class);

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
                int maj = BconArray[0].getMajor();
                int min = BconArray[0].getMinor();
                new jsonLoader(loadInfoJSON(), maj, min);


                if ((BconArray.length )!= 0){

                    startActivity(intent);
                }//if

            }
            }
        );//ranginglistener

    }//onCreate


    private void PrepareLessons(){

        LessList = jsonLoader.getLessList();
        Iterator<Lessons> itr = LessList.iterator();

        while (itr.hasNext()){
            Lessons check = itr.next();
            String Rname = check.getRoomName();
            int Lnum = check.getLesNum();
            String[] LessonSlides=check.getSlides();
            String CardText;
            if ((Lnum == cPosition)){
                for (int i =0; i < check.getnSlides(); i++){
                    CardText = LessonSlides[i];
                    cards2.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                                    .setText(CardText)
                                    .setFootnote(check.getRoomName()+ ": " +check.getLname())
                    );
                }//for
                mCardScroller1.activate();
                setContentView(mCardScroller1);
            }//if
        }//3rd while*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller1.activate();
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
        mCardScroller1.deactivate();
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
