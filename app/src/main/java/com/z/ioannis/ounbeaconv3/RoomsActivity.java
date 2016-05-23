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
 * Created by Ioannis.D on 11-Apr-16.
 * ****************************************************************************
 */
package com.z.ioannis.ounbeaconv3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Adapters.RoomsAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import java.util.List;


public class RoomsActivity extends Activity {

    private CardScrollView mCardScroller;
    private Intent intent;
    private  AudioManager am;
    private Rooms cRoom;
    private Beacons cBcon;


    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        Context context = this;
        cBcon = (Beacons) getIntent().getSerializableExtra("Beacon"); //Retrieve the current closest beacon from the precious Activity
        List<Rooms> Rooms = InfoLoader.getRoomsList();

        for (Rooms room : Rooms){ //Check all the Rooms, one Room at a time
            for(Beacons bcon : room.getBcNames()){ //Check the Beacons associated with the current Room that the app is checking
                if (bcon.getBName().equals(cBcon.getBName())){ //if the name of the Beacon that is being checked, matches the name of the Beacon that was retrieved from the previous Activity
                    cRoom=room; //then the current Room is the Room we want to pass to the adapter.
                    break;
                }
            }
        }

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        intent = new Intent(this,LessonsActivity.class);
        RoomsAdapter rAdapter = new RoomsAdapter(context, cRoom, cBcon);


        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(rAdapter);
        mCardScroller.activate();
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    am.playSoundEffect(Sounds.DISALLOWED);
                }else {
                    am.playSoundEffect(Sounds.TAP);
                    intent.putExtra("Lesson", cBcon.getLssList()[position-1]); //it projects the title of the position-1 because if the position of the AdapterView is 1 then it tis the 0 object of the Lessons List
                    startActivity(intent); //Pass the appropriate Lesson to the next Activity and start it
                }
            }
        });
        setContentView(mCardScroller);

    }//onCreate

    public void onResume(){
        super.onResume();
        mCardScroller.activate();
    }//onResume

    public void onPause(){
        mCardScroller.deactivate();
        super.onPause();
    }//onPause

}
