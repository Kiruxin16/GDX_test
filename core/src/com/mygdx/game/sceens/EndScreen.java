package com.mygdx.game.sceens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.fonts.TitleFont;

import java.awt.*;

public class EndScreen implements Screen {

    private Game game;
    private SpriteBatch batch;
    private String message;
    private TitleFont font;
    private TitleFont subFont;
    private Texture backGround;
    private boolean allowBack;
    private ShapeRenderer shapeRenderer;
    private Color subColor;


    public EndScreen(Game game,String message) {
        this.game =game;
        if (message.equals("LOOSE")){
            this.message="Game over";
            font=new TitleFont(1,55);
            font.setColor(Color.RED);
            allowBack=false;
            subColor=new Color(1,1,1,1);

        }else if(message.equals("WIN")){
            this.message="Well done!!";
            font=new TitleFont(2,55);
            font.setColor(Color.BLUE);
            backGround = new Texture("FoxFin.jpeg");
            allowBack=true;
            subColor=new Color(0.22f,0.45f,1,100);
        }
        shapeRenderer=new ShapeRenderer();
        batch= new SpriteBatch();
        subFont=new TitleFont(3,30);
        subFont.setColor(subColor);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        if(allowBack) {
            batch.begin();
            batch.draw(backGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }
        batch.begin();
        font.render(batch,message,350,300);
        subFont.render(batch,"Try again",410,90);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(subColor);
        shapeRenderer.rect(400,90-subFont.getHeight()-10,160,subFont.getHeight()+12);
        shapeRenderer.end();

        Rectangle tmp=new Rectangle(400,90-subFont.getHeight()-10,160,subFont.getHeight()+12);
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if(tmp.contains(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())) {
                dispose();
                this.game.setScreen(new GameScreen(this.game));
            }
        }



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
        font.dispose();
        subFont.dispose();
        shapeRenderer.dispose();

    }
}
