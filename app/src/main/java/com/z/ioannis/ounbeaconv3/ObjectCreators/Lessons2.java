package com.z.ioannis.ounbeaconv3.ObjectCreators;

/**
 * Created by Ioannis.D on 21-Apr-16.
 */
public class Lessons2 {

    private int LesNum;
    private String Lname;
    private String[] toBcnos;
    private int nSlides;
    private String[] slides;

    public Lessons2(int LesNum, String Lname, String[] toBcnos, int nSlides, String[] slides) {
        this.LesNum = LesNum;
        this.Lname = Lname;
        this.toBcnos = toBcnos;
        this.nSlides = nSlides;
        this.slides = slides;
    }

    public Lessons2() {

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

    public String[] getToBcnos() {
        return toBcnos;
    }

    public void setToBcnos(String[] toBcnos) {
        this.toBcnos = toBcnos;
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
