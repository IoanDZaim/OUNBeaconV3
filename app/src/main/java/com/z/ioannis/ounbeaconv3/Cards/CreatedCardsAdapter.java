package com.z.ioannis.ounbeaconv3.Cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;

import java.util.ArrayList;

/**
 * Created by Ioannis.D on 21-Mar-16.
 */
public class CreatedCardsAdapter extends CardScrollAdapter {

    private ArrayList<CardBuilder> mCards;

    public CreatedCardsAdapter(ArrayList<CardBuilder> mCards, Context context) {
        this.mCards = mCards;
        this.context = context;
    }

    private Context context;


    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public Object getItem(int position) {
        return mCards.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

       /** Card card = new Card(context);

        CardBuilder cc = mCards.get(position);
        if (cc.getText() != null)
            card.setText(cc.getText());

        // Card footer note
        if (cc.getFooter() != null)
            card.setFootnote(cc.getFooter());


        return card.getView();//*/
        return mCards.get(position).getView(view, viewGroup);
    }

    @Override
    public int getPosition(Object item) {
        return mCards.indexOf(item);
    }
}
