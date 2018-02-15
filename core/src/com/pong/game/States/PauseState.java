package com.pong.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.Pong;
import com.pong.game.Sprites.Ball;
import com.pong.game.Sprites.PlayerOne;
import com.pong.game.Sprites.PlayerTwo;

/**
 * Created by nick on 7/9/17.
 */

public class PauseState extends State{
    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private Texture scoreOne;
    private Texture scoreTwo;
    private Ball ball;

    protected PauseState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);
        playerOne = new PlayerOne(0, (Pong.HEIGHT/2 - Pong.PADDLE_HEIGHT/2));
        playerTwo = new PlayerTwo(Pong.WIDTH-Pong.PADDLE_WIDTH, (Pong.HEIGHT/2)-Pong.PADDLE_HEIGHT/2);
        scoreOne = new Texture("Score.png");
        scoreTwo = new Texture("Score.png");
        ball = new Ball(Pong.WIDTH/2-Pong.BALL_WIDTH, Pong.HEIGHT/2);
    }

    protected PauseState(GameStateManager gsm, PlayerOne playerOne, PlayerTwo playerTwo){
        super(gsm);
        cam.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerOne.getPosition().y = (Pong.HEIGHT/2)-Pong.PADDLE_HEIGHT/2;
        this.playerTwo.getPosition().y = (Pong.HEIGHT/2)-Pong.PADDLE_HEIGHT/2;
        scoreOne = new Texture("Score.png");
        scoreTwo = new Texture("Score.png");
        ball = new Ball(Pong.WIDTH/2-Pong.BALL_WIDTH, Pong.HEIGHT/2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && playerOne.getScore() == 0 && playerTwo.getScore() == 0){
            gsm.set(new PlayState(gsm));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && (playerOne.getScore() > 0 || playerTwo.getScore() > 0)){
            gsm.set(new PlayState(gsm, playerOne, playerTwo));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(Pong.playerOneScore == 5){
            gsm.set(new GameOverState(gsm));
        }
        if(Pong.playerTwoScore == 5){
            gsm.set(new GameOverState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(playerOne.getTexture(), playerOne.getPosition().x, playerOne.getPosition().y);
        sb.draw(playerTwo.getTexture(), playerTwo.getPosition().x, playerTwo.getPosition().y);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(scoreOne, Pong.WIDTH/4 - scoreOne.getWidth(), Pong.HEIGHT - scoreOne.getHeight());
        sb.draw(playerOne.getScoreTexture(), Pong.WIDTH/4, Pong.HEIGHT - playerOne.getScoreTexture().getHeight());
        sb.draw(scoreTwo, 3*Pong.WIDTH/4 - playerTwo.getScoreTexture().getWidth(), Pong.HEIGHT - scoreTwo.getHeight());
        sb.draw(playerTwo.getScoreTexture(), 3*Pong.WIDTH/4 + scoreTwo.getWidth() - playerTwo.getScoreTexture().getWidth(), Pong.HEIGHT - playerTwo.getScoreTexture().getHeight());
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
