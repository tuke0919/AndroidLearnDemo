package com.example.horizon.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by liming on 2016/5/15.
 */
public class SecondFragment extends Fragment {

    FragmentTransaction transaction;
    private ImageView imageViewSecond;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //绑定imageSecond到view
        View view = inflater.inflate(R.layout.second_layout,container,false);
        imageViewSecond = (ImageView)view.findViewById(R.id.imageSecond);
        return view;
    }

    //在onCreateView之后执行
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


        //获取ImageFirst的图片资源
        ImageView img = (ImageView) getActivity().findViewById(R.id.imageFirst);
        imageViewSecond.setImageDrawable(img.getDrawable());

        int TAG = getFragmentManager().getBackStackEntryCount();
        Log.d("TAG2",String.valueOf(TAG));
        if(TAG>0)
            Log.d("TAG2",getFragmentManager().getBackStackEntryAt(TAG-1).toString());

        //延迟新建FirstFragment
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirstFragment firstFragment = new FirstFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.hide(SecondFragment.this);
                transaction.add(R.id.mainLayout,firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        },5000);

    }

}
