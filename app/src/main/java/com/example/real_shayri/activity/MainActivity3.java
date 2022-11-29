package com.example.real_shayri.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_shayri.R;
import com.example.real_shayri.adapter.backgroundadapter;
import com.example.real_shayri.adapter.emojiadapter;
import com.example.real_shayri.adapter.fontadapter;
import com.example.real_shayri.adapter.fontstyleadapter;
import com.example.real_shayri.adapter.multiadapter;
import com.example.real_shayri.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    String text;
    LinearLayout linearLayout,linearLayout1;
    Button background,textcolor,share,emoji,font,textsize;
    ImageView expend,reload,download;
    GridView gridView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        linearLayout=findViewById(R.id.liner);
        textView=findViewById(R.id.shayri);
        background=findViewById(R.id.background);
        textcolor=findViewById(R.id.textcolor);
        share=findViewById(R.id.share);
        emoji=findViewById(R.id.emoji);
        listView=findViewById(R.id.list1);
        font=findViewById(R.id.font);
        expend=findViewById(R.id.expand);
        reload=findViewById(R.id.reload);
        textsize=findViewById(R.id.textsize);
        download=findViewById(R.id.download);

        text=getIntent().getStringExtra("shayri");

        textView.setText(text);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(linearLayout);
                System.out.println("bitmap=="+icon);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);
                File f = new File(config.file.getAbsolutePath()+"/shayri"+"image_file"+num+".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(MainActivity3.this, "download file", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        textsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity3.this);
                 view = LayoutInflater.from(MainActivity3.this).inflate(R.layout.textsize,null);

                 seekBar=view.findViewById(R.id.seekbar);

                 bottomSheetDialog.setContentView(view);
                 bottomSheetDialog.show();
                 seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                     @Override
                     public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                         textView.setTextSize(seekBar.getProgress());
                     }

                     @Override
                     public void onStartTrackingTouch(SeekBar seekBar) {

                     }

                     @Override
                     public void onStopTrackingTouch(SeekBar seekBar) {

                     }
                 });
            }
        });

        background.setOnClickListener(v ->
        {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view= LayoutInflater.from(this).inflate(R.layout.background_layout,null);

            gridView=view.findViewById(R.id.grid);
            backgroundadapter adepter = new backgroundadapter(this);
            gridView.setAdapter(adepter);

            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    linearLayout.setBackgroundColor(getResources().getColor(config.colors[i]));
                    bottomSheetDialog.setCancelable(true);
                }
            });
        });
            textcolor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity3.this);
                    bottomSheetDialog.setCancelable(false);
                     view =LayoutInflater.from(MainActivity3.this).inflate(R.layout.font_color,null);

                    gridView=view.findViewById(R.id.grid1);
                    fontadapter fontadapter = new fontadapter(MainActivity3.this);
                    gridView.setAdapter(fontadapter);

                    bottomSheetDialog.setContentView(view);
                    bottomSheetDialog.show();

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            textView.setTextColor(getResources().getColor(config.colors[i]));
                            bottomSheetDialog.setCancelable(true);
                        }
                    });
                }
            });
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bitmap icon = getBitmapFromView(linearLayout);
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    int num=new Random().nextInt(2000);
                    File f = new File(config.file.getAbsolutePath() +"/shayri"+"image_file"+num+".jpg");
                    try {
                        f.createNewFile();
                        FileOutputStream fo = new FileOutputStream(f);
                        fo.write(bytes.toByteArray());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(), "Image", "Share happy !")));
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    startActivity(Intent.createChooser(share, "Share Image"));
                }
            });
            emoji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity3.this);
                    view=LayoutInflater.from(MainActivity3.this).inflate(R.layout.emoji_layout,null);

                    listView=view.findViewById(R.id.list1);
                    emojiadapter adapter = new emojiadapter(MainActivity3.this);
                    listView.setAdapter(adapter);

                    bottomSheetDialog.setContentView(view);
                    bottomSheetDialog.show();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                textView.setText(config.emoji[i]+"\n"+text+"\n"+config.emoji[i]);
                                bottomSheetDialog.dismiss();
                        }
                    });
                }
            });
            font.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity3.this);
                    view=LayoutInflater.from(MainActivity3.this).inflate(R.layout.font_layout,null);

                    gridView=view.findViewById(R.id.list2);
                    fontstyleadapter adapter = new fontstyleadapter(MainActivity3.this);
                    gridView.setNumColumns(config.font.length);
                    gridView.setAdapter(adapter);
               //     gridView.setNumColumns(config.font.length);



                    bottomSheetDialog.setContentView(view);
                    bottomSheetDialog.show();
                    bottomSheetDialog.setCancelable(true);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Typeface typeface = Typeface.createFromAsset(getAssets(),config.font[i]);
                            textView.setTypeface(typeface);
                        }
                    });
                }
            });
        expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity3.this);
                view =LayoutInflater.from(MainActivity3.this).inflate(R.layout.activity_main_multi,null);

                gridView=view.findViewById(R.id.grid3);
                multiadapter adapter = new multiadapter(MainActivity3.this);
                gridView.setAdapter(adapter);


                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        linearLayout.setBackgroundResource(config.gredients[i]);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        reload.setOnClickListener(view -> {
            int min=0;
            int max=config.gredients.length;
            int rendom = new Random().nextInt(max-min)+min;
            linearLayout.setBackgroundResource(config.gredients[rendom]);
        });
    }
    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
}