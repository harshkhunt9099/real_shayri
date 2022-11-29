package com.example.real_shayri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.real_shayri.R;
import com.example.real_shayri.activity.MainActivity1;

public class adapter1 extends BaseAdapter {

    MainActivity1 mainActivity1;
    int img;
    String[] tem;
    public adapter1(MainActivity1 mainActivity1, int img, String[] tem) {
        this.img=img;
        this.tem=tem;
        this.mainActivity1=mainActivity1;
    }

    @Override
    public int getCount() {
        return tem.length;
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
       view= LayoutInflater.from(mainActivity1).inflate(R.layout.activity_item1,viewGroup,false);
        TextView textView = view.findViewById(R.id.tex);
        ImageView imageView = view.findViewById(R.id.im);

        imageView.setImageResource(img);
        textView.setText(tem[i]);

        return view;
    }
}
