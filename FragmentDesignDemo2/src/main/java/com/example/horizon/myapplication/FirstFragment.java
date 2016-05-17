package com.example.horizon.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by liming on 2016/5/15.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {
    ImageView imageView;

    private Button change;
    private Button hide;
    private Button destroy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_layout, container, false);

        //将控件绑定到View上
        imageView = (ImageView) view.findViewById(R.id.imageFirst);
        change = (Button) view.findViewById(R.id.imageChange);
        hide = (Button) view.findViewById(R.id.hide);
        destroy = (Button) view.findViewById(R.id.destroy);
        change.setOnClickListener(this);
        destroy.setOnClickListener(this);
        hide.setOnClickListener(this);

        //Log下显示BackStack元素个数
        int TAG = getFragmentManager().getBackStackEntryCount();
        Log.d("TAG1",String.valueOf(TAG));
        if(TAG>0)
            Log.d("TAG1",getFragmentManager().getBackStackEntryAt(TAG-1).toString());
        return view;
    }


    @Override
    public void onClick(View v) {
        SecondFragment secondFragment = new SecondFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.destroy:
                //保存fragment实例，移除视图与fragment实例的关联
                transaction.addToBackStack(null);
                transaction.replace(R.id.mainLayout, secondFragment);

                break;
            case R.id.imageChange:
                imageView.setImageResource(R.drawable.dog);
                break;
            case R.id.hide:
                transaction.hide(this);
                transaction.addToBackStack(null);
                transaction.add(R.id.mainLayout, secondFragment);
                break;

        }
        transaction.commit();
    }
}
