package com.z.ioannis.ounbeaconv3;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.z.ioannis.ounbeaconv3.Classes.Beacons;

import java.util.List;

/**
 * Created by Ioannis.D on 10-Mar-16.
 */
public class Creator extends CardScrollAdapter {
    private List<Card> mCards;
    private List<Beacons> mData;
    public Creator(List<Card> cards){
        this.mCards = cards;
    }
    @Override
    public int getCount() {
        return mCards.size();
    }
    @Override
    public Object getItem(int i) {
        return mCards.get(i);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return mCards.get(i).getView();
    }
    @Override
    public int getPosition(Object o) {
        return this.mCards.indexOf(o);
    }
}
