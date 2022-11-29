package com.example.real_shayri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.real_shayri.R;
import com.example.real_shayri.activity.MainActivity2;
import com.example.real_shayri.activity.MainActivity3;
import com.example.real_shayri.config;

public class multiadapter extends BaseAdapter {
    MainActivity3 mainActivity3;
    public multiadapter(MainActivity3 mainActivity3) {
        this.mainActivity3=mainActivity3;
    }
    MainActivity2 mainActivity2;
    public multiadapter(MainActivity2 mainActivity2) {
        this.mainActivity2=mainActivity2;
    }

    @Override
    public int getCount() {
        return config.gredients.length;
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
        view= LayoutInflater.from(mainActivity3).inflate(R.layout.multi_item,viewGroup,false);
        TextView textView=view.findViewById(R.id.text);

        textView.setBackgroundResource(config.gredients[i]);
        return view;
    }
}
