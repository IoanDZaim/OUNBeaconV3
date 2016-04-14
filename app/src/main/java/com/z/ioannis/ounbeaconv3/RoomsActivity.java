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
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Adapters.RoomsAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import java.util.ArrayList;


public class RoomsActivity extends Activity {


    private Rooms currentRoom;
    private ArrayList<CardBuilder> cards;
    private CardScrollView mCardScroller;
    private RoomsAdapter roomsAdapter;
    private int cPossition;
    private String[] LessonTitles;
    private String jFile;
    private Intent intent;
    private  AudioManager am;

    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        cards = new ArrayList<>();
        Context context = this;
        jFile = getIntent().getStringExtra(jFile);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        intent = new Intent(this,LessonsActivity.class);
        currentRoom = jsonLoader.getCurrentRoom();
        LessonTitles = currentRoom.getrSlides();
        roomsAdapter = new RoomsAdapter(context, LessonTitles, currentRoom);
/**
        String cardText;
        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                .setText(currentRoom.getWelcMsg())
                .setFootnote(currentRoom.getRoomName()));


        for (int i = 0; i < currentRoom.getNumOfLss(); i++) {
            cardText = LessonTitles[i];
            cards.add(new CardBuilder(context, CardBuilder.Layout.MENU)
                    .setText(cardText)
                    .setFootnote(currentRoom.getRoomName()));
        }//*/


        mCardScroller = new CardScrollView(this);
        //CreatedCardsAdapter adapter = new CreatedCardsAdapter(cards, context);
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
                    intent.putExtra("CURRENT_CARD",cPossition);
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
