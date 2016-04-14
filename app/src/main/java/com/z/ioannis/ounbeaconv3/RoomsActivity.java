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
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Adapters.CreatedCardsAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import java.util.ArrayList;


public class RoomsActivity extends Activity {


    private Rooms currentRoom;
    private ArrayList<CardBuilder> cards;
    private Context context;
    private CardScrollView mCardScroller;
    private int cPossition;
    private String jFile;
    private Intent intent;

    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        cards = new ArrayList<>();
        context = this;
        jFile = getIntent().getStringExtra(jFile);
        currentRoom = jsonLoader.getCurrentRoom();
        String[] LessonTitles;
        LessonTitles = currentRoom.getLssTitles();
        String cardText;
        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                .setText(currentRoom.getWelcMsg())
                .setFootnote(currentRoom.getRoomName()));
        for (int i = 0; i < currentRoom.getNumOfLss(); i++) {
            cardText = LessonTitles[i];
            cards.add(new CardBuilder(context, CardBuilder.Layout.MENU)
                    .setText(cardText)
                    .setFootnote(currentRoom.getRoomName()));
        }
        intent = new Intent(this,LessonsActivity.class);
        mCardScroller = new CardScrollView(this);
        CreatedCardsAdapter adapter = new CreatedCardsAdapter(cards, context);
        mCardScroller.setAdapter(adapter);
        mCardScroller.activate();
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cPossition = mCardScroller.getSelectedItemPosition();
                intent.putExtra("CURRENT_CARD",cPossition);
                startActivity(intent);
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
