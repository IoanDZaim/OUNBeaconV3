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
package com.z.ioannis.ounbeaconv3.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;
import com.z.ioannis.ounbeaconv3.R;

public class RoomsAdapter extends CardScrollAdapter{

    private final Context mContext;
    private String[] mRoomSlides;
    private long[] mValues;
    private Rooms cRoom;

    public RoomsAdapter(Context mContext, String[] mRoomSlides, Rooms cRoom){
        this.mContext = mContext;
        this.mRoomSlides = mRoomSlides;
        this.cRoom = cRoom;
        mValues = new long[cRoom.getNumOfLss()];

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
                        .setText(mRoomSlides[position])
                        .setFootnote(cRoom.getRoomName())
                        .getView();
            }else{
                view = new CardBuilder(mContext, CardBuilder.Layout.EMBED_INSIDE)
                        .setEmbeddedLayout(R.layout.custom_layout)
                        .setFootnote(cRoom.getRoomName())
                        .getView();
                TextView textView1 = (TextView) view.findViewById(R.id.textView);
                textView1.setText(mRoomSlides[position]);
            }
        }
        return view;
    }

    @Override
    public int getPosition(Object o) {
        return 0;
    }

}
