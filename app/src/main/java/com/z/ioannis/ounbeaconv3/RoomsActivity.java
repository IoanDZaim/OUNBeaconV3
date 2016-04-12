package com.z.ioannis.ounbeaconv3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

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
    private int cPossition;
    private Intent intent;

    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        cards = new ArrayList<>();
        context = this;
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
        intent = new Intent(this,LessonsActivity.class);
        mCardScroller = new CardScrollView(this);
        CreatedCardsAdapter adapter = new CreatedCardsAdapter(cards, context);
        mCardScroller.setAdapter(adapter);
        mCardScroller.activate();
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cPossition = mCardScroller.getSelectedItemPosition();
                intent.putExtra("CURRENT_CARD",cPossition);
                startActivity(intent);
            }
        });
        setContentView(mCardScroller);

    }//onCreate


    public void onResume(){
        super.onResume();
        mCardScroller.activate();
    }//onResume

    public void onPause(){
        mCardScroller.deactivate();
        super.onPause();
    }//onPause
/**
    public static int getcPossition() {
        return cPossition;//this whole method needs to change
    }*/
}
