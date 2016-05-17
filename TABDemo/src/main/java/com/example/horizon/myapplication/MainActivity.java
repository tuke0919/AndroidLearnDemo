package com.example.horizon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button VP;
    private Button FM;
    private Button VF;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        VP = (Button)findViewById(R.id.button);
        FM = (Button)findViewById(R.id.button2);
        VF = (Button)findViewById(R.id.button3);


        VP.setOnClickListener(this);
        FM.setOnClickListener(this);
        VF.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                intent = new Intent(MainActivity.this,ViewPagerAndPagerAdapter.class);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this,FragmentAndFragmentManager.class);
                break;
            case R.id.button3:
                intent = new Intent(MainActivity.this,ViewPagerAndFragment.class);
                break;
        }
        startActivity(intent);
    }
}
