package com.hack.define.caloriedetector.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hack.define.caloriedetector.R;
import com.hack.define.caloriedetector.server.FoodServer;
import com.hack.define.caloriedetector.server.model.Food;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_SUBTITLE = "extra_subtitle";

    @BindView(R.id.iv_food)
    ImageView mFoodBanner;
    @BindView(R.id.food_title)
    TextView mTvTitle;
    @BindView(R.id.food_subtitle)
    TextView mTvSubTitle;
    @BindView(R.id.fab_collect)
    FloatingActionButton mFabCollect;
    @BindView(R.id.tv_adv)
    TextView mTvDescription;
    @BindView(R.id.item_tip1)
    TextView mItemTip1;
    @BindView(R.id.item_tip2)
    TextView mItemTip2;
    @BindView(R.id.item_tip3)
    TextView mItemTip3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String subTitle = intent.getStringExtra(EXTRA_SUBTITLE);
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(title)) {
            new LoadDetailTask(this).execute(url);
            mTvTitle.setText(title);
            mTvSubTitle.setText(subTitle);
        }

    }

    public static void startActivity(Context context, String url, String title, String subTitle) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_SUBTITLE, subTitle);
        context.startActivity(intent);
    }

    private static class LoadDetailTask extends AsyncTask<String, Void, Food> {

        WeakReference<FoodDetailActivity> mWeakRefActivity;

        public LoadDetailTask(FoodDetailActivity activity) {
            this.mWeakRefActivity = new WeakReference<>(activity);
        }

        @Override
        protected Food doInBackground(String... params) {
            if (params.length == 1) {
                String url = params[0];
                try {
                    return FoodServer.getSearchContent(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Food food) {
            if (mWeakRefActivity.get() != null) {
                if (food != null) {
                    // TODO: 2017/7/21 update ui
                    Log.d("wtf", food.toString());
                    FoodDetailActivity activity = mWeakRefActivity.get();
                    activity.mTvDescription.setText(food.advantages + "。" + food.disadvantages);
                    activity.mItemTip1.setText(food.running);
                    activity.mItemTip2.setText(food.skipping);
                    activity.mItemTip3.setText(food.aerobics);

                } else {
                    Log.d("wtf", "get detail failed");
                }
            }
        }
    }

}
