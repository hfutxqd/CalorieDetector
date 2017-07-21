package com.hack.define.caloriedetector;

import android.app.Application;
import android.os.AsyncTask;

import com.hack.define.caloriedetector.data.DetectResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by ningjiaqi on 2017/7/21.
 */

public class MyApplication extends Application {

    private static HashMap<String,DetectResult> mData = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mData.clear();
                try {
                    InputStream input = getAssets().open("data.txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String line;
                    while((line = reader.readLine()) != null){
                        String[] dataCol = line.split(",");
                        if (dataCol.length == 4) {
                            String id = dataCol[0];
                            String name = dataCol[1];
                            float energy = Float.valueOf(dataCol[2]);
                            String url = dataCol[3];
                            mData.put(id,new DetectResult(id,name,energy,url));
                        } else if (dataCol.length == 2) {
                            String id = dataCol[0];
                            String name = dataCol[1];
                            mData.put(id,new DetectResult(id,name,-1,null));
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mData.clear();
    }

    public static HashMap<String, DetectResult> getCacheData() {
        return mData;
    }
}
