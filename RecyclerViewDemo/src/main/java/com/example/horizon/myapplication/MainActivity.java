package com.example.horizon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AnimationAdapter commonAdapter;
    private AnimationAdapter staggGridAdapter;
    private ArrayList<Animation> animationList;
    private RecyclerView.LayoutManager layoutManager;

    //初始化数据
    private String[] name = {"超神学院", "勇者大冒险", "航海王", "画江湖", "火影忍者", "墓王之王",
            "秦时明月", "侠岚", "太乙仙魔录"};
    private int[] pic = {R.drawable.chaoshenxueyuan, R.drawable.damaoxian, R.drawable.hanghaiwang,
            R.drawable.huajianghu, R.drawable.huoying, R.drawable.muwangzhiwang,
            R.drawable.qinshimingyue, R.drawable.xialan, R.drawable.xianmolu};

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        AnimOnclick();
    }

    public void initData(){
        //为mRecyclerView设置Adapter
        animationList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            animationList.add(new Animation(pic[i], name[i]));
        }
    }

    public void initAdapter(){
        commonAdapter = new AnimationAdapter(animationList);
        staggGridAdapter = new AnimationAdapter(animationList,true);
    }
    /**
     * 初始化RecyclerView
     */
    public void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        initData();
        initAdapter();

        //采用瀑布流布局时，若高度固定，设置该方法可提高性能
        //mRecyclerView.setHasFixedSize(true);


        //初始化Item显示方式以及适配器
        initLayoutManger('G');

        //横竖分割线，自行添加
//        mRecyclerView.addItemDecoration(new DividerLinearItemDecoration(this, LinearLayout.HORIZONTAL));
//        mRecyclerView.addItemDecoration(new DividerLinearItemDecoration(this, LinearLayout.VERTICAL));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator()); // 默认动画

    }

    /**
     * 根据参数选择布局样式和分割线
     */
    public void initLayoutManger(char layoutType) {
        flag = false;
        switch (layoutType) {
            case 'L':
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                break;
            case 'G':
                layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
                break;
            case 'S':
                layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                flag = true;
                break;
            default:
                return;
        }

        mRecyclerView.setLayoutManager(layoutManager);

        if(flag)
        mRecyclerView.setAdapter(staggGridAdapter);
        else
        mRecyclerView.setAdapter(commonAdapter);
    }

    /**
     * 为Item设置监听
     */
    public void AnimOnclick() {
        if(flag)
        staggGridAdapter.setOnItemClickListener(new AnimationAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                //删除中间的
                Log.d("GHJ",String.valueOf(pos));
                itemRemove(pos);

            }
        });
        else
            commonAdapter.setOnItemClickListener(new AnimationAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int pos) {
                    //删除中间的
                    Log.d("GHJ",String.valueOf(pos));
                    itemRemove(pos);
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                itemAdd(1000);//添加到末尾
                break;
            case R.id.del_item:
                itemRemove(-1);//删除末尾
                break;
            case R.id.linear:
                initLayoutManger('L');
                break;
            case R.id.grid:
                initLayoutManger('G');
                break;
            case R.id.staggeredGrid:
                initLayoutManger('S');
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void itemAdd(int pos){
        //对向下取值没有严格的限制，请自行设定
        if(flag)
            if(pos > staggGridAdapter.getItemCount())
            staggGridAdapter.addItem(staggGridAdapter.getItemCount());
            else
                staggGridAdapter.addItem(pos);
        else
            if(pos > commonAdapter.getItemCount())
            commonAdapter.addItem(commonAdapter.getItemCount());
        else
            commonAdapter.addItem(pos);
    }

    public void itemRemove(int pos){
        //这里对向上取值并没有进行进行严格的限制，请根据需要自行设定
        if(pos<0){
            if(flag)
                staggGridAdapter.delItem(staggGridAdapter.getItemCount() - 1);
            else
                commonAdapter.delItem(commonAdapter.getItemCount() - 1);
        }
        else
        if(flag)
            staggGridAdapter.delItem(pos);
        else
            commonAdapter.delItem(pos);

    }
}
