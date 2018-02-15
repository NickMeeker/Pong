package com.pong.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.Pong;
import com.pong.game.Sprites.Ball;
import com.pong.game.Sprites.PlayerOne;
import com.pong.game.Sprites.PlayerTwo;
import java.util.Random;

/**
 * Created by nick on 7/8/17.
 */

public class PlayState extends State{
    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private Texture scoreOne;
    private Texture scoreTwo;
    private Sound fanfare;
    private Ball ball;


    protected PlayState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);
        playerOne = new PlayerOne(0, (Pong.HEIGHT/2 - Pong.PADDLE_HEIGHT/2));
        playerTwo = new PlayerTwo(Pong.WIDTH-Pong.PADDLE_WIDTH, (Pong.HEIGHT/2)-Pong.PADDLE_HEIGHT/2);
        scoreOne = new Texture("Score.png");
        scoreTwo = new Texture("Score.png");
        fanfare = Gdx.audio.newSound(Gdx.files.internal("sfx_sounds_fanfare2.wav"));
        ball = new Ball(Pong.WIDTH/2-Pong.BALL_WIDTH, Pong.HEIGHT/2);

        Random rand = new Random();
        int i = rand.nextInt(2);
        if(i == 0){
            ball.getVelocity().x = -Pong.BALL_SPEED;
        } else{
            ball.getVelocity().x = Pong.BALL_SPEED;
        }

        i = -15 + rand.nextInt(35);
        if(i <= 0){
            ball.getVelocity().y = -i;
        } else{
            ball.getVelocity().y = i;
        }

    }

    protected PlayState(GameStateManager gsm, PlayerOne playerOne, PlayerTwo playerTwo){
        super(gsm);
        cam.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        scoreOne = new Texture("Score.png");
        scoreTwo = new Texture("Score.png");
        fanfare = Gdx.audio.newSound(Gdx.files.internal("sfx_sounds_fanfare2.wav"));
        ball = new Ball(Pong.WIDTH/2-Pong.BALL_WIDTH, Pong.HEIGHT/2);

        Random rand = new Random();
        int i = rand.nextInt(2);
        if(i == 0){
            ball.getVelocity().x = -Pong.BALL_SPEED;
        } else{
            ball.getVelocity().x = Pong.BALL_SPEED;
        }

        i = -15 + rand.nextInt(35);
        if(i <= 0){
            ball.getVelocity().y = -i;
        } else{
            ball.getVelocity().y = i;
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerOne.up();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerOne.down();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerTwo.up();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            playerTwo.down();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        playerOne.update();
        playerTwo.update();
        ball.update(playerOne, playerTwo);

        if(ball.getPosition().x > Pong.WIDTH){
            Pong.playerOneScore++;
            playerOne.setScore(Pong.playerOneScore);
            playerOne.setScoreTexture(playerOne.getScore());
            fanfare.play(0.5f);
            gsm.set(new PauseState(gsm, playerOne, playerTwo));
        }
        if(ball.getPosition().x + ball.getBounds().width < 0 ) {
            Pong.playerTwoScore++;
            playerTwo.setScore(Pong.playerTwoScore);
            playerTwo.setScoreTexture(playerTwo.getScore());
            fanfare.play(0.5f);
            gsm.set(new PauseState(gsm, playerOne, playerTwo));
        }
     }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(playerOne.getTexture(), playerOne.getPosition().x, playerOne.getPosition().y);
        sb.draw(playerTwo.getTexture(), playerTwo.getPosition().x, playerTwo.getPosition().y);
        sb.draw(scoreOne, Pong.WIDTH/4 - scoreOne.getWidth(), Pong.HEIGHT - scoreOne.getHeight());
        sb.draw(playerOne.getScoreTexture(), Pong.WIDTH/4, Pong.HEIGHT - playerOne.getScoreTexture().getHeight());
        sb.draw(scoreTwo, 3*Pong.WIDTH/4 - playerTwo.getScoreTexture().getWidth(), Pong.HEIGHT - scoreTwo.getHeight());
        sb.draw(playerTwo.getScoreTexture(), 3*Pong.WIDTH/4 + scoreTwo.getWidth() - playerTwo.getScoreTexture().getWidth(), Pong.HEIGHT - playerTwo.getScoreTexture().getHeight());
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
