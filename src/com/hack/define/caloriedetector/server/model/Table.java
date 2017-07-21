package com.hack.define.caloriedetector.server.model;

import java.util.ArrayList;

/**
 * Created by IMXQD on 2017/7/21.
 */
public class Table {
    public ArrayList<String> titles = new ArrayList<String>();
    public ArrayList<String> values = new ArrayList<String>();
    public String[] getRow(int index)
    {
        if(index < titles.size())
            return new String[]{titles.get(index),values.get(index)};
        return null;
    }
}
