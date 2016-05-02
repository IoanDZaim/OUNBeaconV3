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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons2;
import com.z.ioannis.ounbeaconv3.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LessonsAdapter extends CardScrollAdapter{

    private final Context mContext;
    private Lessons2 mLesson;
    private long[] mValues;
    private String photo_url_str = "https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";

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
                /**
                    view = new CardBuilder(mContext, CardBuilder.Layout.TEXT)
                            .setText(mLesson.getSlides()[position])
                            .setFootnote(mLesson.getLname())
                            .getView();
//*/
                view = new CardBuilder(mContext, CardBuilder.Layout.EMBED_INSIDE)
                        .setEmbeddedLayout(R.layout.relative_layout)
                        .setFootnote(mLesson.getLname())
                        .getView();
                TextView mainText = (TextView) view.findViewById(R.id.MainText);
                mainText.setText(mLesson.getSlides()[position]);
                ImageView image = (ImageView) view.findViewById(R.id.imageView);
                URL newurl = null;
                try {
                    newurl = new URL(photo_url_str);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Bitmap mIcon_val = null;
                try {
                    mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.setImageBitmap(mIcon_val);
            }//if
        return view;
    }

    @Override
    public int getPosition(Object o) {
        return 0;
    }


}
