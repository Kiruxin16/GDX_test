package com.mygdx.game.sceens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Animatic;


public class GameScreen implements Screen {

    Game game;

    private SpriteBatch batch;
    private Texture texture;
    private Animatic animatic;
    private boolean direction;
    private float x;
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private Rectangle mapSize;

    private final float RUNSPEED = 7;


    public GameScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        x = 50;
        animatic = new Animatic("atlas/Foxy.atlas", "FoxRun", Animation.PlayMode.LOOP);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        map = new TmxMapLoader().load("maps/map_snow.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        map.getLayers().get("Cam1").getObjects().getByType(RectangleMapObject.class);
        RectangleMapObject tmp = (RectangleMapObject) map.getLayers().get("Cam1").getObjects().get("camera");

        camera.position.x = tmp.getRectangle().x;
        camera.position.y = tmp.getRectangle().y;


        mapSize = ((RectangleMapObject) map.getLayers().get("Cam1").getObjects().get("gadges")).getRectangle();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        ScreenUtils.clear(Color.GRAY);
        animatic.setTime(Gdx.graphics.getDeltaTime());
        // Gdx.input.getX()-animatic.getFrame().getRegionWidth()/2;
        float y = 0; //Gdx.graphics.getHeight()-Gdx.input.getY()-animatic.getFrame().getRegionHeight()/2;
        //time+=Gdx.graphics.getDeltaTime();




        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){

            if (x >0) {
                x -= 7f;
            }

            if(mapSize.x < camera.position.x - Gdx.graphics.getWidth() / 2) {


                if (x - (camera.position.x - camera.viewportWidth / 2) < 100) {
                    camera.position.x -= RUNSPEED;
                }
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            direction = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(x<mapSize.width-200){
                x += 7f;
            }
            if(mapSize.x+mapSize.width > camera.position.x+ Gdx.graphics.getWidth() / 2) {
                if (camera.position.x + camera.viewportWidth / 2 - x < 300) {
                    camera.position.x += RUNSPEED;
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            direction = false;

        }

        if (!animatic.getFrame().isFlipX() && direction ||
                animatic.getFrame().isFlipX() && !direction) {
            animatic.getFrame().flip(true, false);
        }


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mapRenderer.setView(camera);
        mapRenderer.render();
        batch.draw(animatic.getFrame(), x, 115);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            dispose();
            game.setScreen(new MenuScreen(game));


        }


    }

    @Override
    public void resize(int width, int height) {

        camera.viewportWidth = width;
        camera.viewportHeight = height;

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
        mapRenderer.dispose();
        animatic.dispose();


    }
}
