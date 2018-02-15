package com.pong.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.Pong;

/**
 * Created by nick on 7/9/17.
 */

public class GameOverState extends State {
    private Texture victory;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);
        if(Pong.playerOneScore == 5){
            victory = new Texture("Player-1-Wins.png");
        }else{
            victory = new Texture("Player-2-Wins.png");
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            Pong.playerOneScore = 0;
            Pong.playerTwoScore = 0;
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(victory, cam.position.x - victory.getWidth()/2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
