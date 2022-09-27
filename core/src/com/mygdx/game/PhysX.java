package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysX {
    private final World world;
    private final Box2DDebugRenderer debugRenderer;


    public PhysX(MyContList mcl) {
        world = new World(new Vector2(0, -9.81f), true);
        this.debugRenderer = new Box2DDebugRenderer();
        world.setContactListener(mcl);
    }

    public void setGravity(Vector2 gravity) {
        world.setGravity(gravity);
    }

    public void dispose() {
        world.dispose();
        debugRenderer.dispose();

    }

    public void step() {
        world.step(1 / 60f, 3, 3);
    }

    public void debugDraw(OrthographicCamera camera) {
        debugRenderer.render(world, camera.combined);
    }


    public Body addObject(RectangleMapObject recMO) {
        Rectangle rect = recMO.getRectangle();
        String type = (String) recMO.getProperties().get("BodyType");
        BodyDef def = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();

        switch (type) {
            case "DynamicBody":
                def.type = BodyDef.BodyType.DynamicBody;
                break;
            default:
                def.type = BodyDef.BodyType.StaticBody;
                break;
        }


        def.position.set((rect.x + rect.width / 2) / 10, (rect.y + rect.height / 2) / 10);
        def.gravityScale = 10;
        polygonShape.setAsBox(rect.width / 20, rect.height / 20);
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = (float)recMO.getProperties().get("Friction");
        fixtureDef.density = 0.012f;
        fixtureDef.restitution = 0.01f;
        Body body = world.createBody(def);
        if (recMO.getName() != null) {
            body.createFixture(fixtureDef).setUserData(recMO.getName());
            if(recMO.getName().equals("foxBox")){
                polygonShape.setAsBox(rect.width / 120, rect.height / 120,new Vector2(0,-rect.height/20),0);
                body.createFixture(fixtureDef).setSensor(true);
            }
        }
        else{
            body.createFixture(fixtureDef).setUserData("ground");
        }

        polygonShape.dispose();
        return body;


    }
}

