package com.hack.define.caloriedetector.data;

/**
 * Created by ningjiaqi on 2017/7/21.
 */

public class DetectResult {
    public String id;
    public String name;
    public float calorie;
    public String detailUrl;

    public DetectResult(String id, String name, float calorie, String detailUrl) {
        this.id = id;
        this.name = name;
        this.calorie = calorie;
        this.detailUrl = detailUrl;
    }
}
