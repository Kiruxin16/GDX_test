package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animatic {
    private Texture img;
    private Animation<TextureRegion> anm;
    private float time;

    public Animatic(String name, int row, int column,
                    Animation.PlayMode playMode) {
        img = new Texture(name);
        TextureRegion regionBase =new TextureRegion(img);
        int xCnt =regionBase.getRegionWidth()/row;
        int yCnt = regionBase.getRegionHeight()/column;
        TextureRegion[][] regionTable = regionBase.split(xCnt,yCnt);
        TextureRegion[] regionRow = new	TextureRegion[regionTable.length*regionTable[0].length-2];
        int cnt=0;
        for (int i = 0; i < regionTable.length; i++) {
            for (int j = 0; j < regionTable[0].length; j++) {
                if(i==regionTable.length-1&&j>=regionTable[0].length-2){
                    break;
                }
                regionRow[cnt++]=regionTable[i][j];
            }
        }
        anm = new Animation<>(1/8f,regionRow);
        anm.setPlayMode(playMode);
        time=0;// += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrame(){
        return anm.getKeyFrame(time);
    }
    public void setTime(float time){
        this.time+=time;
    }
    public void zeroTime() {
        this.time =0;
    }
    public boolean isAnimationOver(){
        return anm.isAnimationFinished(time);
    }
    public void setPlayMode(Animation.PlayMode playMode){
        anm.setPlayMode(playMode);
    }

    public void dispose(){
        img.dispose();
    }
}
