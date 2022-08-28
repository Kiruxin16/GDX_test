package com.mygdx.game.sceens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Animatic;

import java.awt.*;

public class GameScreen implements Screen {

    Game game;

    private SpriteBatch batch;
    private Texture texture;
    private Animatic animatic;
    private boolean direction;
    private float x;



    public GameScreen(Game game) {
        this.game = game;
        batch= new SpriteBatch();
        x=0;
        animatic = new Animatic("atlas/Foxy.atlas","FoxRun", Animation.PlayMode.LOOP);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.GOLD);
        animatic.setTime(Gdx.graphics.getDeltaTime());
        // Gdx.input.getX()-animatic.getFrame().getRegionWidth()/2;
        float y=0; //Gdx.graphics.getHeight()-Gdx.input.getY()-animatic.getFrame().getRegionHeight()/2;
        //time+=Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (x > 0) {
                x -= 7f;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            direction = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(x<Gdx.graphics.getWidth()-animatic.getFrame().getRegionWidth()) {
                x += 7f;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            direction = false;

        }

        if (!animatic.getFrame().isFlipX()&&direction ||
                animatic.getFrame().isFlipX()&&!direction){
            animatic.getFrame().flip(true,false);
        }




        batch.begin();
        batch.draw(animatic.getFrame(),x,0);

        batch.end();



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();

    }
}
