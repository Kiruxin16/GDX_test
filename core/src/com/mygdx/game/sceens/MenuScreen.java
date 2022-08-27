package com.mygdx.game.sceens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class MenuScreen implements Screen {

    Game game;
    private SpriteBatch batch;
    private Texture texture;

    private Rectangle startRect;
    private ShapeRenderer shapeRenderer;

    public MenuScreen(Game game) {
        this.game = game;
        batch= new SpriteBatch();
        texture = new Texture("FoxTitle.png");

        startRect = new Rectangle(0,0,texture.getWidth(),texture.getHeight());
        shapeRenderer  =new ShapeRenderer();



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.GOLD);

        batch.begin();
        batch.draw(texture,0,0);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(startRect.x, startRect.y, startRect.width,startRect.height);
        shapeRenderer.end();


        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if(startRect.contains(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())) {
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
        texture.dispose();
        shapeRenderer.dispose();

    }
}
