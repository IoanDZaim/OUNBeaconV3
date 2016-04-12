package com.z.ioannis.ounbeaconv3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;
import com.z.ioannis.ounbeaconv3.Adapters.CreatedCardsAdapter;
import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ioannis.D on 11-Apr-16.
 */
public class LessonsActivity extends Activity {

    private List<Lessons> LessList;
    private ArrayList<CardBuilder> cards;
    private CardScrollView mCardScroller;
    private Context context;
    private int cPossition;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        context = this;
        cards = new ArrayList<>();
        LessList = jsonLoader.getLessList();

        for (Lessons lesson : LessList){
            int Lnum = lesson.getLesNum();
            String[] LessonSlides = lesson.getSlides();
            String cardText;
            if (Lnum == cPossition){
                for (int i =0; i < lesson.getnSlides(); i++){
                    cardText = LessonSlides[i];
                    cards.add(new CardBuilder(context, CardBuilder.Layout.TEXT)
                            .setText(cardText)
                            .setFootnote(lesson.getRoomName()+ ": " +lesson.getLname())
                    );
                }//for
                mCardScroller.activate();
                setContentView(mCardScroller);
            }//if
        }//for

        mCardScroller = new CardScrollView(this);
        CreatedCardsAdapter adapter = new CreatedCardsAdapter(cards, context);

        mCardScroller.setAdapter(adapter);
        mCardScroller.activate();
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

}
