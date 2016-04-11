package com.z.ioannis.ounbeaconv3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Adapters.CreatedCardsAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Rooms;

import java.util.ArrayList;

/**
 * Created by Ioannis.D on 11-Apr-16.
 */
public class RoomsActivity extends Activity {


    private Rooms currentRoom;
    private ArrayList<CardBuilder> cards;
    private Context context;
    private CardScrollView mCardScroller;

    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        cards = new ArrayList<>();

        currentRoom = jsonLoader.getCurrentRoom();

        String[] LessonTitles;
        LessonTitles = currentRoom.getLssTitles();
        String cardText;
        cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                .setText(currentRoom.getWelcMsg())
                .setFootnote(currentRoom.getRoomName()));
        for (int i = 0; i < currentRoom.getNumOfLss(); i++) {
            cardText = LessonTitles[i];
            cards.add(new CardBuilder(context, CardBuilder.Layout.MENU)
                    .setText(cardText)
                    .setFootnote(currentRoom.getRoomName()));
        }

        mCardScroller = new CardScrollView(this);
        CreatedCardsAdapter adapter = new CreatedCardsAdapter(cards, context);
        mCardScroller.setAdapter(adapter);
        mCardScroller.activate();
        setContentView(mCardScroller);
    }//onCreate


    public void onResume(){
        super.onResume();
    }//onResume

    public void onPause(){
        super.onPause();
    }//onPause
}
