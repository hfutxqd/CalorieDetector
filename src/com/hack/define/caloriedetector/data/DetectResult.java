package com.hack.define.caloriedetector.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by ningjiaqi on 2017/7/21.
 */
@Table(database = FoodDatabase.class)
public class DetectResult extends BaseModel implements Parcelable {
    @PrimaryKey
    public String id;
    @Column
    public String name;
    @Column
    public float calorie;
    @Column
    public String detailUrl;
    @Column
    public int isCollected;

    public DetectResult() {
    }

    public DetectResult(String id, String name, float calorie, String detailUrl) {
        this.id = id;
        this.name = name;
        this.calorie = calorie;
        this.detailUrl = detailUrl;
    }

    public void setIsCollected(int isCollected) {
        this.isCollected = isCollected;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeFloat(this.calorie);
        dest.writeString(this.detailUrl);
        dest.writeInt(this.isCollected);
    }

    protected DetectResult(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.calorie = in.readFloat();
        this.detailUrl = in.readString();
        this.isCollected = in.readInt();
    }

    public static final Parcelable.Creator<DetectResult> CREATOR = new Parcelable.Creator<DetectResult>() {
        @Override
        public DetectResult createFromParcel(Parcel source) {
            return new DetectResult(source);
        }

        @Override
        public DetectResult[] newArray(int size) {
            return new DetectResult[size];
        }
    };
}
