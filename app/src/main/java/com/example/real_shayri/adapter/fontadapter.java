package com.example.real_shayri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.real_shayri.R;
import com.example.real_shayri.activity.MainActivity3;
import com.example.real_shayri.config;

public class fontadapter extends BaseAdapter {
    MainActivity3 mainActivity3;
    public fontadapter(MainActivity3 mainActivity3) {
        this.mainActivity3=mainActivity3;
    }

    @Override
    public int getCount() {
        return config.colors.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(mainActivity3).inflate(R.layout.font_coloritem,viewGroup,false);
        LinearLayout linearLayout =view.findViewById(R.id.view1);
        linearLayout.setBackgroundColor(mainActivity3.getResources().getColor(config.colors[i]));
        return view;
    }
}
