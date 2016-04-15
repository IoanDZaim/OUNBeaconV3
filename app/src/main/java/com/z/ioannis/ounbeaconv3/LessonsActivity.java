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
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Adapters.LessonsAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;

import java.util.ArrayList;
import java.util.List;


public class LessonsActivity extends Activity {

    private List<Lessons> LessList;
    private ArrayList<CardBuilder> cards;
    private CardScrollView mCardScroller;
    private int cPoss;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Context context = this;
        cards = new ArrayList<>();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        LessList = jsonLoader.getLessList();
        cPoss = getIntent().getIntExtra("CURRENT_CARD",cPoss);

/**
        for (Lessons lesson : LessList){
            int Lnum = lesson.getLesNum();
            String[] LessonSlides = lesson.getSlides();
            String cardText;
            if (Lnum == cPoss){
                for (int i =0; i < lesson.getnSlides(); i++){
                    cardText = LessonSlides[i];
                    cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                            .setText(cardText)
                            .setFootnote(lesson.getRoomName()+ ": " +lesson.getLname())
                    );
                }//for
            }//if
        }//for//*/

        mCardScroller = new CardScrollView(this);
        LessonsAdapter lessonsAdapter = new LessonsAdapter(context, LessList, cPoss);
        mCardScroller.setAdapter(lessonsAdapter);
        mCardScroller.activate();
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
