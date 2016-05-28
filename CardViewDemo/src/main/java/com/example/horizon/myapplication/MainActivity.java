package com.example.horizon.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Toolbar toolbar;
    Bitmap bitmap;
    Palette palette;
    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        changeImage(1);

    }

    public void initView() {
        imageView = (ImageView) findViewById(R.id.Image);
        cardView1 = (CardView) findViewById(R.id.CardView1);
        cardView2 = (CardView) findViewById(R.id.CardView2);
        cardView3 = (CardView) findViewById(R.id.CardView3);
        cardView4 = (CardView) findViewById(R.id.CardView4);
        cardView5 = (CardView) findViewById(R.id.CardView5);
        cardView6 = (CardView) findViewById(R.id.CardView6);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.icon1:
                changeImage(1);
                break;
            case R.id.icon2:
                changeImage(2);
                break;
            case R.id.icon3:
                changeImage(3);
                break;
        }
        return true;
    }

    public void changeImage(int flag) {

        //没有获取到的表现，用于区分
        cardView1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        cardView2.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        cardView3.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        cardView4.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        cardView5.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        cardView6.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

        if (flag == 1) {
            imageView.setImageResource(R.drawable.aaa);
            imageView.setTag(R.drawable.aaa);
        } else if (flag == 2) {
            imageView.setImageResource(R.drawable.qqq);
            imageView.setTag(R.drawable.qqq);
        } else {
            imageView.setImageResource(R.drawable.zzz);
            imageView.setTag(R.drawable.zzz);
        }

        //获取ImageView的drawable的资源ID
        if (imageView != null) {
            bitmap = BitmapFactory.decodeResource(getResources(), (Integer) imageView.getTag());
        }

        //Palette
        palette = Palette.from(bitmap).generate();

        if (palette.getVibrantSwatch() != null)
            cardView1.setCardBackgroundColor(palette.getVibrantSwatch().getRgb());


        if (palette.getDarkVibrantSwatch() != null)
            cardView2.setCardBackgroundColor(palette.getDarkVibrantSwatch().getRgb());

        if (palette.getLightVibrantSwatch() != null)
            cardView3.setCardBackgroundColor(palette.getLightVibrantSwatch().getRgb());

        if (palette.getMutedSwatch() != null) {
            cardView4.setCardBackgroundColor(palette.getMutedSwatch().getRgb());
            toolbar.setBackgroundColor(palette.getMutedSwatch().getRgb());
        }

        if (palette.getDarkMutedSwatch() != null){
            cardView5.setCardBackgroundColor(palette.getDarkMutedSwatch().getRgb());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(palette.getDarkMutedSwatch().getRgb());
            }
        }

        if (palette.getLightMutedSwatch() != null)
            cardView6.setCardBackgroundColor(palette.getLightMutedSwatch().getRgb());


    }
}
