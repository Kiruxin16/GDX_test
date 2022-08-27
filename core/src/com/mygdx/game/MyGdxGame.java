package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Animatic animatic;
	private float time;
	private boolean direction;
	TextureRegion textureRegion;

	private float x;


	@Override
	public void create () {
		batch = new SpriteBatch();
		x=0;


		animatic = new Animatic("FoxSprite.png",3,3, Animation.PlayMode.LOOP);




	}

	@Override
	public void render () {
		ScreenUtils.clear(0.89f, 0.84f, 0.76f, 1);
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
	public void dispose () {
		batch.dispose();
		animatic.dispose();
	}
}
