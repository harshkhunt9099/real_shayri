package com.example.real_shayri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.real_shayri.R;
import com.example.real_shayri.activity.MainActivity;
import com.example.real_shayri.activity.MainActivity1;
import com.example.real_shayri.config;

public class adapter extends BaseAdapter {

    MainActivity mainActivity;
    public adapter(MainActivity mainActivity, String[] text) {
        this.mainActivity=mainActivity;
    }


    @Override
    public int getCount() {
        return config.text.length;
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
        view= LayoutInflater.from(mainActivity).inflate(R.layout.activity_item,viewGroup,false);
        TextView textView =view.findViewById(R.id.tex);
        ImageView imageView=view.findViewById(R.id.img);

        textView.setText(config.text[i]);
        imageView.setImageResource(config.img[i]);

        return view;
    }
}
