package com.hack.define.caloriedetector.data;

/**
 * Created by ningjiaqi on 2017/7/21.
 */

public class Food {
    public String id;
    public String name;
    public float calorie;
    public String detailUrl;

    public Food(String id, String name, float calorie, String detailUrl) {
        this.id = id;
        this.name = name;
        this.calorie = calorie;
        this.detailUrl = detailUrl;
    }
}
