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
 * Created by Ioannis.D on 07-Apr-16.
 * ****************************************************************************
 */
package com.z.ioannis.ounbeaconv3.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons2;

public class LessonsAdapter extends CardScrollAdapter{

    private final Context mContext;
    private Lessons2 mLesson;
    private long[] mValues;

    public LessonsAdapter(Context mContext, Lessons2 mLesson){
        this.mContext = mContext;
        mValues = new long[mLesson.getSlides().length];
        this.mLesson = mLesson;
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

            if (currentView == null) {
                    view = new CardBuilder(mContext, CardBuilder.Layout.TEXT)
                            .setText(mLesson.getSlides()[position])
                            .setFootnote(mLesson.getLname())
                            .getView();

            }

        return view;
    }

    @Override
    public int getPosition(Object o) {
        return 0;
    }

}
