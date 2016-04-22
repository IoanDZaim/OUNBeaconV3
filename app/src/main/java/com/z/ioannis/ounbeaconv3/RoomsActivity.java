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
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;


public class RoomsActivity extends Activity {


    private Rooms currentRoom;
    private CardScrollView mCardScroller;
    private int cPossition;
    private String jFile;
    private Intent intent;
    private  AudioManager am;

    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        Context context = this;
        jFile = getIntent().getStringExtra("JSON");
        int maj = getIntent().getIntExtra("MAJ", -1);
        int min = getIntent().getIntExtra("MIN", -1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);



        intent = new Intent(this,LessonsActivity.class);
        currentRoom = jsonLoader.getCurrentRoom();
        String[] LessonTitles = currentRoom.getrSlides();
        RoomsAdapter roomsAdapter = new RoomsAdapter(context, LessonTitles, currentRoom);


        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(roomsAdapter);
        mCardScroller.activate();
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cPossition = mCardScroller.getSelectedItemPosition();
                if (cPossition == 0){
                    am.playSoundEffect(Sounds.DISALLOWED);
                }else {
                    am.playSoundEffect(Sounds.TAP);
                    new lessonLoader(jFile, currentRoom.getRoomName(), cPossition);
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
