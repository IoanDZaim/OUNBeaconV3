package com.z.ioannis.ounbeaconv3.ObjectCreators;

/**
 * Created by Ioannis.D on 21-Apr-16.
 */
public class Rooms2 {

    public Rooms2(String roomID, String[] BcNames, String roomName, int numOfLss, String[] rSlides) {

        this.RoomID = roomID;
        this.BcNames = BcNames;
        this.RoomName = roomName;
        this.NumOfLss = numOfLss;
        this.rSlides = rSlides;
    }

    public Rooms2(){

    }
    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String[] getBcNames() {
        return BcNames;
    }

    public void setBcNames(String[] BcName) {
        this.BcNames = BcName;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public int getNumOfLss() {
        return NumOfLss;
    }

    public void setNumOfLss(int numOfLss) {
        NumOfLss = numOfLss;
    }

    public String[] getrSlides() {
        return rSlides;
    }

    public void setrSlides(String[] rSlides) {
        this.rSlides = rSlides;
    }

    private String RoomID;
    private String[] BcNames;
    private String RoomName;
    private int NumOfLss;
    private String[] rSlides;

}
