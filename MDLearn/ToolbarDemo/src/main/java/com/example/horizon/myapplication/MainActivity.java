package com.example.horizon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("My Title");
        // Sub Title
        toolbar.setSubtitle("Sub title");

        setSupportActionBar(toolbar);
        //setNavigationIcon必须设置setSupportActionBar后才会生效，否则会显示一个back按钮。
        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setOnMenuItemClickListener(menuItemListener);

    }

    private Toolbar.OnMenuItemClickListener menuItemListener = new
            Toolbar.OnMenuItemClickListener(){

                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    String str = "clicked ";
                    switch (menuItem.getItemId()){
                        case R.id.action_community:
                            str+="community";break;
                        case R.id.action_edit:
                            str+="edit";break;
                        case R.id.action_share:
                            str+="share";break;
                        case R.id.action_scan:
                            str+="share";break;
                        case R.id.action_support:
                            str+="share";break;
                        default:
                            break;
                    }
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    return false;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

}

