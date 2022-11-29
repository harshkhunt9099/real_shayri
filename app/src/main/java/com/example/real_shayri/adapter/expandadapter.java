package com.example.real_shayri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.real_shayri.R;
import com.example.real_shayri.activity.MainActivity2;
import com.example.real_shayri.config;

public class expandadapter extends BaseAdapter {
    MainActivity2 mainActivity2;
    public expandadapter(MainActivity2 mainActivity2) {
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
        view= LayoutInflater.from(mainActivity2).inflate(R.layout.expand_item,viewGroup,false);
        TextView textView=view.findViewById(R.id.text);

        textView.setBackgroundResource(config.gredients[i]);
        return view;
    }
}
