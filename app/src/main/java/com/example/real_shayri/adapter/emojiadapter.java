package com.example.real_shayri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.real_shayri.R;
import com.example.real_shayri.activity.MainActivity3;
import com.example.real_shayri.config;

public class emojiadapter extends BaseAdapter {
    MainActivity3 mainActivity3;
    public emojiadapter(MainActivity3 mainActivity3) {
        this.mainActivity3=mainActivity3;
    }

    @Override
    public int getCount() {
        return config.emoji.length;
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
        view= LayoutInflater.from(mainActivity3).inflate(R.layout.emoji_item,viewGroup,false);
        TextView textView = view.findViewById(R.id.texte);
        textView.setText(config.emoji[i]);

        return view;
    }
}
