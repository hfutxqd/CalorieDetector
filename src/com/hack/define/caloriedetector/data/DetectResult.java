package com.hack.define.caloriedetector.data;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by ningjiaqi on 2017/7/21.
 */
@Table(database = FoodDatabase.class)
public class DetectResult extends BaseModel {
    @PrimaryKey
    public String id;
    @Column
    public String name;
    @Column
    public float calorie;
    @Column
    public String detailUrl;

    public DetectResult() {
    }

    public DetectResult(String id, String name, float calorie, String detailUrl) {
        this.id = id;
        this.name = name;
        this.calorie = calorie;
        this.detailUrl = detailUrl;
    }
}
