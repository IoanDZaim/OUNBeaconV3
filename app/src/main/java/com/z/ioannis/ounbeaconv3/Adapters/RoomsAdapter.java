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
 * Created by Ioannis.D on 22-Apr-16.
 * ****************************************************************************
 */

package com.z.ioannis.ounbeaconv3.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;
import com.z.ioannis.ounbeaconv3.R;

public class RoomsAdapter extends CardScrollAdapter{

    private final Context mContext;
    private long[] mValues;
    private Rooms mRoom;
    private Lessons[] mLesson;

    public RoomsAdapter(Context mContext, Rooms mRoom, Beacons mBcon){
        this.mContext = mContext;
        this.mRoom = mRoom;
        this.mLesson = mBcon.getLssList();
        mValues = new long[(mBcon.getLssList().length)+1];

    }


    @Override
    public int getCount() {
        return mValues.length;
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position <= mValues.length){
            return mValues[position];
        }
        return null;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup viewGroup) {
        View view = null;
        if (currentView == null){
            if (position==0){
                view = new CardBuilder(mContext, CardBuilder.Layout.TEXT)
                        .setText(mRoom.getWelcMsg())
                        .setFootnote(mRoom.getRoomName())
                        .getView();
            }else{
                view = new CardBuilder(mContext, CardBuilder.Layout.EMBED_INSIDE)
                        .setEmbeddedLayout(R.layout.custom_layout) //The menu cards use a custom Layout so as to fit the whole name of the Lesson title to the screen
                        .setFootnote(mRoom.getRoomName())
                        .getView();
                TextView textView1 = (TextView) view.findViewById(R.id.textView);
                textView1.setText(mLesson[position-1].getLname());//MUST find a better solution to this (position-1) {if it's stupid but it works, it works}
            }//if the position is 0, then it's the Welcome Message card, else is a "menu" card. The position is -1 because the Lesson Titles are from a different array.
        }
        return view;
    }

    @Override
    public int getPosition(Object o) {
        return 0;
    }


}
