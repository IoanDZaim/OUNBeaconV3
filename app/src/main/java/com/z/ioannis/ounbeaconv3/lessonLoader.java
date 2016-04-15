/**
 * ****************************************************************************
 * Copyright (C) 2016 Open Universiteit Nederland
 *
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors: Ioannis D. Zaimidis
 * Created by Ioannis.D on 15-Apr-16.
 * ****************************************************************************
 */

package com.z.ioannis.ounbeaconv3;

import com.z.ioannis.ounbeaconv3.ObjectCreators.Lessons;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class lessonLoader {

    private static Lessons cLesson;


    public lessonLoader (String jFile, String roomName, int lnum){
        Lessons lesson;
        JSONObject jsonInfo;
        String[] SlidesTxts;
        try{
            jsonInfo = new JSONObject(jFile);
            JSONArray jsonArray3 = jsonInfo.optJSONArray("lessons");
                for (int i = 0; i < jsonArray3.length(); i++) {
                    JSONObject jsonObject = jsonArray3.getJSONObject(i);
                    String rName = jsonObject.getString("roomname");
                    int lesnum = jsonObject.getInt("num");
                    if (rName.equals(roomName) && lesnum == lnum){
                        lesson = new Lessons();
                        lesson.setLesNum(lesnum);
                        String lname = jsonObject.getString("name");
                        lesson.setLname(lname);
                        lesson.setRoomName(rName);
                        int nSlides = jsonObject.getInt("NumOfSlides");
                        lesson.setnSlides(nSlides);
                        SlidesTxts = new String[nSlides];
                        for (int k = 0; k < nSlides; k++) {
                            String slide = jsonObject.getString("Slide " + k);
                            SlidesTxts[k] = slide;
                        }//for in for3
                        lesson.setSlides(SlidesTxts);
                        cLesson = lesson;
                    }//if 2nd
                }//for 3
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }//lessonLoader

    public static Lessons getcLesson() {
        return cLesson;
    }

}
