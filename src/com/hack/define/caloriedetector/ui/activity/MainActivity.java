package com.hack.define.caloriedetector.ui.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.hack.define.caloriedetector.R;
import com.hack.define.caloriedetector.ui.adapter.FoodAdapter;

import io.codetail.animation.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    FloatingActionButton mFab;
    RecyclerView mRecyclerView;
    NestedScrollView mScrollView;
    TextView cardTv;
    FoodAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new FoodAdapter();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mScrollView = (NestedScrollView) findViewById(R.id.main_scroll_view);
        cardTv = (TextView) findViewById(R.id.main_card_text);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mScrollView.smoothScrollTo(0, 0);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = findViewById(R.id.cutscenes_view);
                // get the center for the clipping circle
                int cx = (mFab.getLeft() + mFab.getRight()) / 2;
                int cy = (mFab.getTop() + mFab.getBottom()) / 2;
                v.setVisibility(View.VISIBLE);
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator animator =
                        ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(800);
                animator.start();
                startActivity(new Intent(MainActivity.this, ClassifierActivity.class));
                overridePendingTransition(0, 0);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.update();
        findViewById(R.id.cutscenes_view).setVisibility(View.INVISIBLE);
    }
}
