package com.example.horizon.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.horizon.myapplication.fragment_v4.ContactsFragment;
import com.example.horizon.myapplication.fragment_v4.MessageFragment;
import com.example.horizon.myapplication.fragment_v4.NewsFragment;
import com.example.horizon.myapplication.fragment_v4.SettingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 2016/5/4.
 */
public class ViewPagerAndFragment extends AppCompatActivity implements View.OnClickListener{
    /**
     * 用于展示的Fragment
     */
    private MessageFragment messageFragment;
    private ContactsFragment contactsFragment;
    private NewsFragment newsFragment;
    private SettingFragment settingFragment;

    /**
     * 界面布局
     */
    private View messageLayout;
    private View contactsLayout;
    private View newsLayout;
    private View settingLayout;

    /**
     * 在Tab布局上显示图标的控件
     */
    private ImageView messageImage;
    private ImageView contactsImage;
    private ImageView newsImage;
    private ImageView settingImage;

    /**
     * 在Tab布局上显示标题的控件
     */
    private TextView messageText;
    private TextView contactsText;
    private TextView newsText;
    private TextView settingText;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    private List<Fragment> mList;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_fragment);
        fragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager)findViewById(R.id.viewPager_2);
        // 初始化布局元素
        initViews();

    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {

        //这里实例化选项卡
        messageLayout = findViewById(R.id.message_layout);
        contactsLayout = findViewById(R.id.contacts_layout);
        newsLayout = findViewById(R.id.news_layout);
        settingLayout = findViewById(R.id.setting_layout);

        messageImage = (ImageView) findViewById(R.id.message_image);
        contactsImage = (ImageView) findViewById(R.id.contacts_image);
        newsImage = (ImageView) findViewById(R.id.news_image);
        settingImage = (ImageView) findViewById(R.id.setting_image);
        messageText = (TextView) findViewById(R.id.message_text);
        contactsText = (TextView) findViewById(R.id.contacts_text);
        newsText = (TextView) findViewById(R.id.news_text);
        settingText = (TextView) findViewById(R.id.setting_text);


        messageLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);


        //mList用于保存显示Content的fragment,别和前面的选项卡搞混了
        mList = new ArrayList<>();
        messageFragment = new MessageFragment();
        contactsFragment = new ContactsFragment();
        newsFragment = new NewsFragment();
        settingFragment = new SettingFragment();

        mList.add(messageFragment);
        mList.add(contactsFragment);
        mList.add(newsFragment);
        mList.add(settingFragment);

        //为ViewPager设置Adapter
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }

        });

        //设置默认点击
        mViewPager.setCurrentItem(0);
        messageImage.setImageResource(R.drawable.message_selected);
        messageText.setTextColor(Color.WHITE);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//            Toast.makeText(ViewPagerAdapter.this,String.valueOf(mList.get(position).getId()),
//                    Toast.LENGTH_SHORT).show();
                clearSelection();

                switch (position)
                {
                    case 0:
                        // 当点击了消息tab时，改变控件的图片和文字颜色
                        messageImage.setImageResource(R.drawable.message_selected);
                        messageText.setTextColor(Color.WHITE);

                        break;
                    case 1:
                        contactsImage.setImageResource(R.drawable.contacts_selected);
                        contactsText.setTextColor(Color.WHITE);
                        break;
                    case 2:
                        newsImage.setImageResource(R.drawable.news_selected);
                        newsText.setTextColor(Color.WHITE);
                        break;
                    case 3:
                        settingImage.setImageResource(R.drawable.setting_selected);
                        settingText.setTextColor(Color.WHITE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        messageImage.setImageResource(R.drawable.message_unselected);
        messageText.setTextColor(Color.parseColor("#82858b"));
        contactsImage.setImageResource(R.drawable.contacts_unselected);
        contactsText.setTextColor(Color.parseColor("#82858b"));
        newsImage.setImageResource(R.drawable.news_unselected);
        newsText.setTextColor(Color.parseColor("#82858b"));
        settingImage.setImageResource(R.drawable.setting_unselected);
        settingText.setTextColor(Color.parseColor("#82858b"));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.message_layout:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                mViewPager.setCurrentItem(0);
                messageImage.setImageResource(R.drawable.message_selected);
                messageText.setTextColor(Color.WHITE);
                break;
            case R.id.contacts_layout:
                mViewPager.setCurrentItem(1);
                contactsImage.setImageResource(R.drawable.contacts_selected);
                contactsText.setTextColor(Color.WHITE);
                break;
            case R.id.news_layout:
                mViewPager.setCurrentItem(2);
                newsImage.setImageResource(R.drawable.news_selected);
                newsText.setTextColor(Color.WHITE);
                break;
            case R.id.setting_layout:
                mViewPager.setCurrentItem(3);
                settingImage.setImageResource(R.drawable.setting_selected);
                settingText.setTextColor(Color.WHITE);
                break;
        }
    }
}
