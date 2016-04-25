package com.z.ioannis.ounbeaconv3.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Beacons2;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons2;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms2;
import com.z.ioannis.ounbeaconv3.R;

/**
 * Created by Ioannis.D on 22-Apr-16.
 */
public class RoomsAdapter2 extends CardScrollAdapter{

    private final Context mContext;
    private long[] mValues;
    private Rooms2 mRoom;
    private Beacons2 mBcon;
    private Lessons2[] mLesson;

    public RoomsAdapter2(Context mContext, Rooms2 mRoom, Beacons2 mBcon){
        this.mContext = mContext;
        this.mRoom = mRoom;
        this.mBcon = mBcon;
        mLesson = mBcon.getLssList();
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
                        .setEmbeddedLayout(R.layout.custom_layout)
                        .setFootnote(mRoom.getRoomName())
                        .getView();
                TextView textView1 = (TextView) view.findViewById(R.id.textView);
                textView1.setText(mLesson[position-1].getLname());//MUST find a better solution to this
            }
        }
        return view;
    }

    @Override
    public int getPosition(Object o) {
        return 0;
    }


}
