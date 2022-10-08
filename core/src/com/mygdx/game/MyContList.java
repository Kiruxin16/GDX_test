package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class MyContList implements ContactListener {

    private float speedMult;
    private boolean allowJump;
    private String state;
   // private World world;

    //public MyContList(World world) {
    public MyContList() {
        speedMult = 1f;
        allowJump =false;
        //this.world=world;
        state="GAME";
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

            if (((tmpA.equals("foxBox_left")||(tmpA.equals("foxBox_right"))  && tmpB.equals("Stone"))||
                    (tmpA.equals("Stone")   && (tmpA.equals("foxBox_left")||(tmpA.equals("foxBox_right")))))) {
                speedMult =0.4f;
            }
            if (tmpA.equals("foxBox")||tmpB.equals("foxBox")){
                allowJump=true;
            }

            if ((tmpA.equals("foxBox")   && tmpB.equals("Spikes"))||
                    (tmpA.equals("Spikes")   && tmpB.equals("foxBox"))) {
                state="LOOSE";
            }
            if ((tmpA.equals("foxBox_right")   && tmpB.equals("Finish"))||
                    (tmpA.equals("Finish")   && tmpB.equals("foxBox_right"))) {
                state="WIN";
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
            if (
                    ((tmpA.equals("foxBox_left")||tmpA.equals("foxBox_right"))  && tmpB.equals("Stone"))||
                    (tmpA.equals("Stone")   && (tmpA.equals("foxBox_left")||tmpA.equals("foxBox_right")))
            ) {
                speedMult = 1f;
            }
            if (tmpA.equals("foxBox")||tmpB.equals("foxBox")){
                allowJump=false;
            }

        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();


        if(a.getUserData()!=null && b.getUserData()!=null) {
            String tmpA = (String) a.getUserData();
            String tmpB = (String) b.getUserData();



            if (((tmpA.equals("foxBox_left") || (tmpA.equals("foxBox_right")) && tmpB.equals("Stone")) ||
                    (tmpA.equals("Stone") && (tmpA.equals("foxBox_left") || (tmpA.equals("foxBox_right")))))) {
                speedMult = 0.3f;
            }

        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
/*        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();


        if(a.getUserData()!=null && b.getUserData()!=null) {
            String tmpA = (String) a.getUserData();
            String tmpB = (String) b.getUserData();

            if (((tmpA.equals("foxBox_left") || (tmpA.equals("foxBox_right")) && tmpB.equals("Stone")) ||
                    (tmpA.equals("Stone") && (tmpA.equals("foxBox_left") || (tmpA.equals("foxBox_right")))))) {
                speedMult = 1f;
            }

        }*/
    }

    public float changeSpeed(){
        return speedMult;
    }
    public boolean isAllowJump(){
        return allowJump;
    }

    public String getState(){
        return state;
    }

/*    public void destroyBody(Body body){
        world.destroyBody(body);
    }*/
}
