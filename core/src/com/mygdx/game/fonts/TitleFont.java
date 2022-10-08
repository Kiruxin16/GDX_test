package com.mygdx.game.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TitleFont {
    private BitmapFont titles;

    public TitleFont(int fontStyle,int size) {
        FreeTypeFontGenerator generator;
        switch (fontStyle){
            case 1: generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/28.ttf"));
            break;
            case 2: generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Sofia-Regular.otf"));
            break;
            default:generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/FFF_Tusj.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter parameter =new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size =size;
        parameter.characters="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm!.";
        titles=generator.generateFont(parameter);
        generator.dispose();
    }

    public void render(SpriteBatch batch,String str,int x,int y){
        titles.draw(batch,str,x,y);
    }

    public int getHeight(){
        return (int) titles.getCapHeight()+1;
    }
    public void setColor(Color color){
        titles.setColor(color);
    }

    public void dispose(){
        titles.dispose();
    }
}
