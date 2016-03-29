package com.z.ioannis.ounbeaconv3.Classes;

/**
 * Created by Ioannis.D on 14-Mar-16.
 */
public class Rooms{

    public Rooms(String roomID, String BcName, String roomName, String welcMsg, int numOfLss, String lssTitles) {

        this.RoomID = roomID;
        this.BcName= BcName;
        this.RoomName = roomName;
        this.WelcMsg = welcMsg;
        this.NumOfLss = numOfLss;
        this.LssTitles = lssTitles;
    }

    public Rooms(){

    }
    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getBcName() {
        return BcName;
    }

    public void setBcName(String BcName) {
        this.BcName = BcName;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getWelcMsg() {
        return WelcMsg;
    }

    public void setWelcMsg(String welcMsg) {
        WelcMsg = welcMsg;
    }

    public int getNumOfLss() {
        return NumOfLss;
    }

    public void setNumOfLss(int numOfLss) {
        NumOfLss = numOfLss;
    }

    public String getLssTitles() {
        return LssTitles;
    }

    public void setLssTitles(String lssTitles) {
        LssTitles = lssTitles;
    }

    private String RoomID;
    private String BcName;
    private String RoomName;
    private String WelcMsg;
    private int NumOfLss;
    private String LssTitles;

    @Override
    public String toString() {
        return "Rooms{" +
                "RoomID='" + RoomID + '\'' +
                ", BcName='" + BcName + '\'' +
                ", RoomName='" + RoomName + '\'' +
                ", WelcMsg='" + WelcMsg + '\'' +
                ", NumOfLss=" + NumOfLss +
                ", LssTitles=" + LssTitles +
                '}';
    }

}
