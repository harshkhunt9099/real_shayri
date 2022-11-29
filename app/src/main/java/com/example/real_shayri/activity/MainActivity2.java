package com.example.real_shayri.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_shayri.R;
import com.example.real_shayri.adapter.expandadapter;
import com.example.real_shayri.adapter.multiadapter;
import com.example.real_shayri.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    TextView textView,title;
    int pos,text;
    String[] arr;
    GridView gridView;
    ImageView copy,prev,next,pencil,share,expend,relod;
    ActionBar actionBar;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView=findViewById(R.id.page);
        copy=findViewById(R.id.copy);
        prev=findViewById(R.id.prev);
        title=findViewById(R.id.title);
        next=findViewById(R.id.next);
        pencil=findViewById(R.id.pencil);
        share=findViewById(R.id.share);
        expend=findViewById(R.id.expand1);
        relod=findViewById(R.id.reload);
        linearLayout=findViewById(R.id.liner);

        pos=getIntent().getIntExtra("pos",0);
        arr=getIntent().getStringArrayExtra("arr");
        text=getIntent().getIntExtra("text",0);

        actionBar=getSupportActionBar();
        actionBar.setTitle(config.text[text]);

        textView.setText(arr[pos]);
        title.setText((pos+1)+"/"+ arr.length);

        expend.setOnClickListener(view ->
        {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            view= LayoutInflater.from(this).inflate(R.layout.expand_layout,null);

            gridView=view.findViewById(R.id.grid3);
            expandadapter adapter = new expandadapter(this);
            gridView.setAdapter(adapter);

            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textView.setBackgroundResource(config.gredients[i]);
                    bottomSheetDialog.dismiss();
                }
            });
        });
        relod.setOnClickListener(view -> {
            int min=0;
            int max=config.gredients.length;
            int random = new Random().nextInt(max-min)+min;
            textView.setBackgroundResource(config.gredients[random]);
        });

        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("shayri",textView.getText().toString());
                startActivity(intent);
            }
        });

        copy.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label",arr[pos]);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "copied", Toast.LENGTH_SHORT).show();
        });
        prev.setOnClickListener(view -> {

            if (pos>0)
            {
                pos--;
                textView.setText(arr[pos]);
                title.setText((pos+1)+"/"+ arr.length);
            }
        });
        next.setOnClickListener(view -> {

            if (pos<arr.length-1)
            {
                pos++;
                textView.setText(arr[pos]);
                title.setText((pos+1)+"/"+ arr.length);
            }
        });
        share.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody =arr[pos];
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "share"));
        });


    }
}