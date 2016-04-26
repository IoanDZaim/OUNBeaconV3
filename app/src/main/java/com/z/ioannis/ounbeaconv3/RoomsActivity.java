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
import com.z.ioannis.ounbeaconv3.Adapters.RoomsAdapter2;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons2;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms2;

import java.util.List;


public class RoomsActivity extends Activity {

    private CardScrollView mCardScroller;
    private int cPossition;
    private String jFile;
    private Intent intent;
    private  AudioManager am;
    private Rooms2 cRoom;
    private Beacons2 cBcon;
    private String les;

    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        Context context = this;
        jFile = getIntent().getStringExtra("JSON");
        int maj = getIntent().getIntExtra("MAJ", -1);
        int min = getIntent().getIntExtra("MIN", -1);
        List<Rooms2> Rooms = testLoader.getRoomsList();

        for (Rooms2 room : Rooms){
            for(Beacons2 bcon : room.getBcNames()){
                if ((bcon.getMajor()==maj)&&(bcon.getMinor()==min)){
                    cBcon=bcon;
                    cRoom=room;
                    break;
                }
            }
        }

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        intent = new Intent(this,LessonsActivity.class);
        RoomsAdapter2 rAdapter = new RoomsAdapter2(context, cRoom, cBcon);


        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(rAdapter);
        mCardScroller.activate();
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cPossition = mCardScroller.getSelectedItemPosition();
                if (cPossition == 0){
                    am.playSoundEffect(Sounds.DISALLOWED);
                }else {
                    am.playSoundEffect(Sounds.TAP);
                    les=cBcon.getLssList()[(position)-1].getLesID();
                    intent.putExtra("Lesson",les);
                    startActivity(intent);
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
