package com.hack.define.caloriedetector.server.model;

/**
 * Created by IMXQD on 2017/7/21.
 */
public class SearchRes {
    public String url;
    public String title;
    public String describe;
    public float calory;

    @Override
    public String toString() {
        return "SearchRes{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", calory=" + calory +
                '}';
    }
}
