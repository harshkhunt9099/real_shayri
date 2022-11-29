package com.example.real_shayri.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.real_shayri.R;
import com.example.real_shayri.adapter.adapter1;
import com.example.real_shayri.config;

public class MainActivity1 extends AppCompatActivity {

    ListView listView;
    String[] tem;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        listView=findViewById(R.id.list1);

        int pos=getIntent().getIntExtra("pos",0);

        actionBar=getSupportActionBar();
        actionBar.setTitle(config.text[pos]);

        if (pos==0)
        {
            tem= config.god;
        }
        if (pos==1)
        {
            tem=config.holiimg;
        }
        if (pos==2)
        {
            tem=config.husband;
        }
        if (pos==3)
        {
            tem=config.love;
        }
        if (pos==4)
        {
            tem=config.mariied;
        }
        if (pos==5)
        {
            tem=config.motivational;
        }
        if (pos==6)
        {
            tem=config.sad;
        }

        adapter1 a = new adapter1(this,config.img[pos],tem);
        listView.setAdapter(a);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
                intent.putExtra("pos",i);
                intent.putExtra("arr",tem);
                intent.putExtra("text",pos);
                startActivity(intent);
            }
        });
    }
}