package com.example.horizon.myapplication;

/**
 * Created by liming on 2016/4/7.
 */
public class Animation {
    private int imageId;
    private String animationName;


    public Animation(int imageId, String animationName) {
        this.imageId = imageId;
        this.animationName = animationName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }
}
