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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Animatic;
import com.mygdx.game.PhysX;


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

    private Array<RectangleMapObject> solidGround;
    private ShapeRenderer shapeRenderer;
    private Rectangle foxBox;

    private final float RUNSPEED = 6;

    private PhysX physX;
    private Body body;
    private float rot;
    private final int[] grounds;
    private final int[] forward;


    public GameScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        x = 50;
        animatic = new Animatic("atlas/Foxy.atlas", "FoxRun", Animation.PlayMode.LOOP);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        map = new TmxMapLoader().load("maps/map_snow.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        //map.getLayers().get("refPoints").getObjects().getByType(RectangleMapObject.class);
        RectangleMapObject tmp = (RectangleMapObject) map.getLayers().get("cam1").getObjects().get("camera");
        physX=new PhysX();

        solidGround = map.getLayers().get("refPoints").getObjects().getByType(RectangleMapObject.class);
        for (RectangleMapObject r:solidGround){
            physX.addObject(r);
        }
        RectangleMapObject fb= (RectangleMapObject)map.getLayers().get("cam1").getObjects().get("foxBox");
        foxBox= fb.getRectangle();
        body= physX.addObject(fb);
        forward =new int[1];
        forward[0]=map.getLayers().getIndex("forward");
        grounds= new int[1];
        grounds[0]=map.getLayers().getIndex("ground");



        camera.position.x = tmp.getRectangle().x;
        camera.position.y = tmp.getRectangle().y;

        shapeRenderer = new ShapeRenderer();
        rot=0;

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

        Vector2 fall=body.getLinearVelocity();
        String str = String.format("x: %.1f y: %.1f",fall.x+0.01f,fall.y+1f);
        Gdx.graphics.setTitle(str);



        if ((body.getPosition().x)*10 - (camera.position.x - camera.viewportWidth / 2) < 200) {
            //camera.position.x -= RUNSPEED;
            camera.position.set(-200+(body.getPosition().x)*10+camera.viewportWidth / 2,camera.position.y,camera.position.z);
        }
        if (camera.position.x + camera.viewportWidth / 2 - (body.getPosition().x)*10 < 200) {
            //camera.position.x += RUNSPEED;
            camera.position.set(200+(body.getPosition().x)*10-camera.viewportWidth / 2,camera.position.y,camera.position.z);
        }

        /**
         * buttons*/
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.setLinearVelocity(new Vector2(-36,body.getLinearVelocity().y));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            direction = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.setLinearVelocity(new Vector2(36,body.getLinearVelocity().y));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            direction = false;
        }




        if (!animatic.getFrame().isFlipX() && direction ||
                animatic.getFrame().isFlipX() && !direction) {
            animatic.getFrame().flip(true, false);
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            body.applyForce(new Vector2(0f,2500f),body.getWorldCenter(),true);
        }



        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mapRenderer.setView(camera);
        mapRenderer.render(grounds);
        batch.draw(animatic.getFrame(), (body.getPosition().x*10)-animatic.getFrame().getRegionWidth()/2,(body.getPosition().y*10)-animatic.getFrame().getRegionHeight()/2,
                animatic.getFrame().getRegionWidth()/2,animatic.getFrame().getRegionHeight()/2,
                animatic.getFrame().getRegionWidth(), animatic.getFrame().getRegionHeight(),
                1,1,body.getAngle()*57.29577f);
        batch.end();
        mapRenderer.render(forward);

/*        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.CYAN);
        for (RectangleMapObject r:solidGround){
            shapeRenderer.rect(r.getRectangle().x,r.getRectangle().y,r.getRectangle().width,r.getRectangle().height);
        }
        shapeRenderer.rect((body.getPosition().x*10)-foxBox.width/2,(body.getPosition().y*10)-foxBox.height/2,
                foxBox.width/2,foxBox.height/2,
                foxBox.width, foxBox.height,
                1,1,body.getAngle()*57.29577f);


        shapeRenderer.end();
*/
        physX.step();
        physX.debugDraw(camera);
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
