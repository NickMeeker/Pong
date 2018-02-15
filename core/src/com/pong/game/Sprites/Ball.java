package com.pong.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pong.game.Pong;
import java.util.Random;

/**
 * Created by nick on 7/9/17.
 */

public class Ball {
    private float dyMultiplier;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Sound blip;

    public Ball(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("ball.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        blip = Gdx.audio.newSound(Gdx.files.internal("sfx_sounds_Blip11.wav"));
    }

    public void update(PlayerOne playerOne, PlayerTwo playerTwo){
        // If the ball collides with player one, then
        //     If the ball is in the top half of the paddle, it should bounce upward
        //     If the ball is in the bottom half of the paddle, it should bounce downward

        /*
         *  The paddle is sectioned into vertical quarters. If the ball hits any of the four quarters, a multiplier is applied to its
         *  y velocity, causing it to bounce differently relative to where it lands on the paddle.
         *  In order to prevent the game from becoming repetitive of a random factor of 1 or 2 is also applied to the y velocity, allowing for
         *  some randomness in the direction the ball bounces.
         */

        if(bounds.x < playerOne.getBounds().width
                && position.y+bounds.height >= playerOne.getPosition().y
                && position.y <= playerOne.getPosition().y + playerOne.getBounds().height)
                {

            Random rand = new Random();
            int random = rand.nextInt(2);
            if(position.y+bounds.height < playerOne.getBounds().y + playerOne.getBounds().height/4){
                dyMultiplier = -20*random;
            } else if(position.y + bounds.y > playerOne.getBounds().y + playerOne.getBounds().height/4 && position.y+bounds.height < playerOne.getBounds().y + playerOne.getBounds().height/2){
                dyMultiplier = -10*random;
            }else if(position.y + bounds.y > playerOne.getBounds().y + playerOne.getBounds().height/2 && position.y+bounds.height < playerOne.getBounds().y + 3*playerOne.getBounds().height/4){
                dyMultiplier = 10*random;
            }else{
                dyMultiplier = 20*random;
            }

            // System.out.println(dyMultiplier);
            position.x = playerOne.getBounds().width;
            velocity.x *= -1;
            velocity.y = 1;
            velocity.y *= dyMultiplier;
            blip.play(0.5f);
        }

        if(bounds.x + getBounds().width > playerTwo.getBounds().x
                && position.y+bounds.height >= playerTwo.getPosition().y
                && position.y <= playerTwo.getPosition().y + playerTwo.getBounds().height){

            if(position.y+bounds.height < playerTwo.getBounds().y + playerTwo.getBounds().height/4){
                dyMultiplier = -20;
            } else if(position.y + bounds.y > playerTwo.getBounds().y + playerTwo.getBounds().height/4 && position.y+bounds.height < playerTwo.getBounds().y + playerTwo.getBounds().height/2){
                dyMultiplier = -10;
            }else if(position.y + bounds.y > playerTwo.getBounds().y + playerTwo.getBounds().height/2 && position.y+bounds.height < playerTwo.getBounds().y + 3*playerTwo.getBounds().height/4){
                dyMultiplier = 10;
            }else{
                dyMultiplier = 20;
            }
            position.x = playerTwo.getPosition().x - getBounds().width;
            velocity.x *= -1;
            velocity.y = 1;
            velocity.y *= dyMultiplier;
            blip.play(0.5f);
        }

        // Bounce off the ceiling
        if(bounds.y + bounds.height > Pong.HEIGHT){
            position.y = Pong.HEIGHT - bounds.height;
            velocity.y *= -1;
        }

        // And the floor
        if(bounds.y < 0){
            position.y = 0;
            velocity.y *= -1;
        }

        position.x += velocity.x;
        position.y += velocity.y;

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
}
