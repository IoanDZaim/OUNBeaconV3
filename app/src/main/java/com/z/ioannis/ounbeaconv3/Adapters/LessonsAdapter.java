package com.z.ioannis.ounbeaconv3.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;

import java.util.List;

/**
 * Created by Ioannis.D on 07-Apr-16.
 */
public class LessonsAdapter extends CardScrollAdapter{

    private final Context mContext;
    private List<Lessons> mLesson;
    private int[] mValues;

    public LessonsAdapter(Context mContext, List<Lessons> mLesson){
        this.mContext = mContext;
        mValues = new int[mLesson.size()];
        this.mLesson = mLesson;
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
        View view = null;
        if (currentView == null){

        }
        return view;
    }

    @Override
    public int getPosition(Object o) {
        return 0;
    }
}
