package com.z.ioannis.ounbeaconv3.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import java.util.List;

/**
 * Created by Ioannis.D on 07-Apr-16.
 */
public class RoomsAdapter extends CardScrollAdapter{

    private final Context mContext;
    private List<Rooms> mRooms;
    private int[] mValues;

    public RoomsAdapter (Context mContext, List<Rooms> mRooms){
        this.mContext = mContext;
        mValues = new int[mRooms.size()];
        this.mRooms = mRooms;
    }


    @Override
    public int getCount() {
        return mValues.length;
    }

    @Override
    public Object getItem(int i) {
        if (i >= 0 && i < mValues.length){
            return mValues[i];
        }
        return null;
    }

    @Override
    public View getView(int i, View currentView, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getPosition(Object o) {
        return 0;
    }
}
