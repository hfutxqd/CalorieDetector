package com.hack.define.caloriedetector.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hack.define.caloriedetector.R;
import com.hack.define.caloriedetector.data.DetectResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by imxqd on 2017/7/21.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    List<DetectResult> mData;

    public FoodAdapter() {
        mData = DetectResult.find(DetectResult.class, null);
    }

    public void update() {
        mData.clear();
        mData.addAll(DetectResult.find(DetectResult.class, null));
        notifyDataSetChanged();
    }

    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoodHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FoodHolder holder, int position) {
        holder.text1.setText(mData.get(position).name);
        holder.text2.setText(mData.get(position).id);
        holder.energy.setText(String.format(Locale.getDefault(),
                "%.1f卡/100克", mData.get(position).calorie));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class FoodHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;
        TextView energy;
        ImageView icon;

        public FoodHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.food_item_text);
            text2 = (TextView) itemView.findViewById(R.id.food_item_text2);
            energy = (TextView) itemView.findViewById(R.id.food_item_energy);
            icon = (ImageView) itemView.findViewById(R.id.food_item_icon);
        }
    }
}
