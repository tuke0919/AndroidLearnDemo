package com.example.horizon.myapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liming on 2016/4/7.
 */
public class AnimationAdapter extends RecyclerView.Adapter<AnimationAdapter.MyHolder>
        implements View.OnClickListener {

    private ArrayList<Animation> animationList;
    private List<Integer> mHeights;

    private Boolean IsStaggeredGrid;

    private final Random random = new Random();
    /**
     * 构造方法，获取数据
     */
    public AnimationAdapter(ArrayList<Animation> animationList) {
        this.animationList = animationList;
        mHeights = new ArrayList<>();
        IsStaggeredGrid = false;
    }

    public AnimationAdapter(ArrayList<Animation> animationList, Boolean IsStaggeredGrid) {

        this.IsStaggeredGrid = IsStaggeredGrid;
        this.animationList = animationList;
        mHeights = new ArrayList<>();
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //创建itemView生成ViewHolder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animation_item, parent, false);

        //将创建的View注册点击事件
        itemView.setOnClickListener(this);
        return new MyHolder(itemView);
    }

    @Override
    public int getItemCount() {
        //返回list的长度
        return animationList.size();
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        //将数据保存在itemView的Tag中，以便点击时进行获取
        Animation animation = animationList.get(position);
        holder.itemView.setTag(position);

        if (IsStaggeredGrid) {
            //随机产生Item高度
            if (mHeights.size() <= position) {
                mHeights.add((int) (200*Math.random())+120);
            }

            //通过LayoutParams设置Item的高度
            ViewGroup.LayoutParams lp = holder.getImageView().getLayoutParams();
            lp.height = mHeights.get(position);
            holder.getImageView().setLayoutParams(lp);
            holder.itemView.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
            holder.getImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
        }


        //为Item设置image和name
        holder.getImageView().setImageResource(animation.getImageId());

    }

    //添加Item
    public void addItem(int position) {
        Animation animation;
        if(getItemCount() == 0){
            Log.d("TAG","池子都空了还填个毛线！！");
            return;
        }
        animation = animationList.get((int) (Math.random() * getItemCount()));
        animationList.add(position, animation);
        //使总position+1
        notifyItemInserted(position);
        notifyDataSetChanged();//更新数据表
    }

    //移除Item
    public void delItem(int position) {
        if(position < 0){
            Log.d("TAG","池子都空了还删个毛线！！");
            return;
        }
        animationList.remove(position);
        //使总position-1
        notifyItemRemoved(position);
        notifyDataSetChanged();//更新数据表
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

    //为Item创建监听接口
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position);
    }


    //实例化接口接收外部设置的listener
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}


