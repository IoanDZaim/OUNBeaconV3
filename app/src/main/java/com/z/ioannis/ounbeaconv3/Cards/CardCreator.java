package com.z.ioannis.ounbeaconv3.Cards;

/**
 * Created by Ioannis.D on 21-Mar-16.
 */
public class CardCreator {

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public CardCreator(String text, String footer) {
        this.text = text;
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "CardCreator{" +
                "text='" + text + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }

    private String text;
    private String footer;


}
