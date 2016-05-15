package com.example.horizon.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentFirst;
    Fragment fragmentSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentFirst = new FirstFragment();
        fragmentSecond = new SecondFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //特别注意，同一个事务只能用于一次提交，
        // 这里保证每次commit都会有一个新的事务进行支持
        transaction = getFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.add_first:
                transaction.add(R.id.mainLayout, fragmentFirst);
                break;
            case R.id.add_second:
                transaction.add(R.id.mainLayout, fragmentSecond);
                break;
            case R.id.remove_first:
                transaction.remove(fragmentFirst);
                break;
            case R.id.remove_second:
                transaction.remove(fragmentSecond);
                break;
        }
        transaction.commit();
        return true;
    }
}
