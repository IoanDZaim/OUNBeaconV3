package com.z.ioannis.ounbeaconv3.Classes;

import java.util.Arrays;

/**
 * Created by Ioannis.D on 22-Mar-16.
 */
public class Lessons {

    private int LesNum;
    private String Lname;
    private String roomName;
    private int nSlides;
    private String[] slides;

    public Lessons(int lesNum, String lname, String roomName, int nSlides, String[] slides) {
        LesNum = lesNum;
        Lname = lname;
        this.roomName = roomName;
        this.nSlides = nSlides;
        this.slides = slides;
    }

    public Lessons() {

    }

    @Override
    public String toString() {
        return "Lessons{" +
                "LesNum=" + LesNum +
                ", Lname='" + Lname + '\'' +
                ", roomName='" + roomName + '\'' +
                ", nSlides=" + nSlides +
                ", slides=" + Arrays.toString(slides) +
                '}';
    }

    public int getLesNum() {
        return LesNum;
    }

    public void setLesNum(int lesNum) {
        LesNum = lesNum;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getnSlides() {
        return nSlides;
    }

    public void setnSlides(int nSlides) {
        this.nSlides = nSlides;
    }

    public String[] getSlides() {
        return slides;
    }

    public void setSlides(String[] slides) {
        this.slides = slides;
    }


}
