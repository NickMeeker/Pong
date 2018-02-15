package com.pong.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pong.game.Pong;
/**
 * Created by nick on 7/8/17.
 */

public class PlayerTwo {
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private int score;
    private Texture scoreTexture;

    public PlayerTwo(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("paddle.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        score = Pong.playerTwoScore;
        scoreTexture = new Texture("0.png");


    }

    public void update(){
        if(position.y > Pong.HEIGHT - bounds.getHeight()){
            position.y = Pong.HEIGHT - bounds.getHeight();
        }
        if(position.y < 0){
            position.y = 0;
        }

        bounds.x = position.x;
        bounds.y = position.y;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getScore() {
        return score;
    }

    public Texture getScoreTexture() {
        return scoreTexture;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreTexture(int score) {
        switch(score){
            case 1:
                scoreTexture = new Texture("1.png");
                break;
            case 2:
                scoreTexture = new Texture("2.png");
                break;
            case 3:
                scoreTexture = new Texture("3.png");
                break;
            case 4:
                scoreTexture = new Texture("4.png");
                break;
            case 5:
                scoreTexture = new Texture("5.png");
                break;
        }
    }

    public void up(){
        velocity.y = Pong.PLAYER_SPEED;
        position.y += velocity.y;
    }

    public void down(){
        velocity.y = Pong.PLAYER_SPEED*-1;
        position.y += velocity.y;
    }
}
