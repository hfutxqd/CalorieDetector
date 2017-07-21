package com.hack.define.caloriedetector.ui.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.hack.define.caloriedetector.R;
import com.hack.define.caloriedetector.ui.adapter.FoodAdapter;

import io.codetail.animation.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    FloatingActionButton fab;
    RecyclerView foodRecyclerView;
    TextView cardTv;
    FoodAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new FoodAdapter();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        cardTv = (TextView) findViewById(R.id.main_card_text);
        foodRecyclerView = (RecyclerView) findViewById(R.id.main_list);

        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodRecyclerView.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = findViewById(R.id.cutscenes_view);
                // get the center for the clipping circle
                int cx = (fab.getLeft() + fab.getRight()) / 2;
                int cy = (fab.getTop() + fab.getBottom()) / 2;
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
        findViewById(R.id.cutscenes_view).setVisibility(View.INVISIBLE);
    }
}
