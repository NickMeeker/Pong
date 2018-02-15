package com.pong.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pong.game.Pong;

/**
 * Created by nick on 7/8/17.
 */

public class MenuState extends State{
    private Texture play;
    private Sound select;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);
        play = new Texture("play.png");
        select = Gdx.audio.newSound(Gdx.files.internal("sfx_menu_select1.wav"));
    }



    @Override
    protected void handleInput() {
        if(Gdx.input.getX() >= Pong.WIDTH/2 - play.getWidth()/2 && Gdx.input.getX() <= Pong.WIDTH/2 + play.getWidth()/2
                && Pong.HEIGHT - Gdx.input.getY() >= Pong.HEIGHT/2 && Pong.HEIGHT- Gdx.input.getY() <= Pong.HEIGHT/2 + play.getHeight()){
            // System.out.println("loikj");
            play = new Texture("play_mouse.png");
        }else{
            play = new Texture("play.png");
        }
        if(Gdx.input.justTouched()){
            play = new Texture("play_mouse.png");
            select.play(0.5f);
            gsm.set(new PauseState(gsm));
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
        sb.draw(play, cam.position.x - play.getWidth()/2, cam.position.y);
        // System.out.println("test");
        sb.end();
    }

    @Override
    public void dispose() {
        play.dispose();
    }


}
