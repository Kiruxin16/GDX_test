package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class MyContList implements ContactListener {

    private float speedMult;
    private boolean allowJump;
   // private World world;

    //public MyContList(World world) {
    public MyContList() {
        speedMult = 1f;
        allowJump =false;
        //this.world=world;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();


        if(a.getUserData()!=null && b.getUserData()!=null) {
            String tmpA =(String) a.getUserData();
            String tmpB =(String) b.getUserData();

            if ((tmpA.equals("foxBox")   && tmpB.equals("Snowdrift"))||
                    (tmpA.equals("Snowdrift")   && tmpB.equals("foxBox"))) {
                speedMult =0.5f;

            }
            if (tmpA.equals("foxBox")||tmpB.equals("foxBox")){
                allowJump=true;
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
      //  boolean isSen = b.getBody().getFixtureList().get(1).isSensor();


        if(a.getUserData()!=null && b.getUserData()!=null) {
            String tmpA =(String) a.getUserData();
            String tmpB =(String) b.getUserData();
            if ((tmpA.equals("foxBox")   && tmpB.equals("Snowdrift"))||
                    (tmpA.equals("Snowdrift")   && tmpB.equals("foxBox"))) {
                speedMult =1f;
            }
            if (tmpA.equals("foxBox")||tmpB.equals("foxBox")){
                allowJump=false;
            }

        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public float changeSpeed(){
        return speedMult;
    }
    public boolean isAllowJump(){
        return allowJump;
    }

/*    public void destroyBody(Body body){
        world.destroyBody(body);
    }*/
}
