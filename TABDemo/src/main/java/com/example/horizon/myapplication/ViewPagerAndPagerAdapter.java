package com.example.horizon.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 2016/5/3.
 */
public class ViewPagerAndPagerAdapter extends AppCompatActivity {
    private ViewPager mViewPager;

    /**
     * 各个界面布局
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
     * 在Tab布局上显示文字的控件
     */
    private TextView messageText;
    private TextView contactsText;
    private TextView newsText;
    private TextView settingText;

    List<View> mList;

    LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_pageradapter);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager_1);
        mList = new ArrayList<>();
        mInflater = LayoutInflater.from(this);
        messageLayout = mInflater.inflate(R.layout.message_layout,null);
        contactsLayout = mInflater.inflate(R.layout.contacts_layout,null);
        newsLayout = mInflater.inflate(R.layout.news_layout,null);
        settingLayout = mInflater.inflate(R.layout.setting_layout,null);
//        //绑定复合选项卡并进行监听
//        messageLayout = findViewById(R.id.message_layout);
//        contactsLayout = findViewById(R.id.contacts_layout);
//        newsLayout = findViewById(R.id.news_layout);
//        settingLayout = findViewById(R.id.setting_layout);

        mList.add(messageLayout);
        mList.add(contactsLayout);
        mList.add(newsLayout);
        mList.add(settingLayout);


        //绑定布局内的子控件
        messageImage = (ImageView) findViewById(R.id.message_image);
        contactsImage = (ImageView) findViewById(R.id.contacts_image);
        newsImage = (ImageView) findViewById(R.id.news_image);
        settingImage = (ImageView) findViewById(R.id.setting_image);
        messageText = (TextView) findViewById(R.id.message_text);
        contactsText = (TextView) findViewById(R.id.contacts_text);
        newsText = (TextView) findViewById(R.id.news_text);
        settingText = (TextView) findViewById(R.id.setting_text);

        //为ViewPager设置Adapter
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mList.get(position));
                return mList.get(position);
            }

        });

        //设置默认选中的Item
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

    /**每次选中前清空当前状态*/
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

}
