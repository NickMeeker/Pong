package com.pong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.States.GameStateManager;
import com.pong.game.States.MenuState;

public class Pong extends ApplicationAdapter {
	// Constants declaration handled here
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int PADDLE_HEIGHT = 210;
	public static final int PADDLE_WIDTH =26;
	public static final int BALL_WIDTH = 50;
	public static final int PLAYER_SPEED = 50;
	public static final int BALL_SPEED = 25;

	public static final String TITLE = "Pong";

	// TODO: refactor playerScore implementation with overloaded constructors instead of global variables
	public static int playerOneScore = 0;
	public static int playerTwoScore = 0;

	// Gamestate manager and spirtebatch obejcts
	private GameStateManager gsm;
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
