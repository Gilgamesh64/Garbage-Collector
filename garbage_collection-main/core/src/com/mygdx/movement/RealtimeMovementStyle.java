package com.mygdx.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.animations.PlayerAnimationManager;

public class RealtimeMovementStyle extends MovementStyle{
    private final Actor player;
    private int lastDirection;

    public RealtimeMovementStyle(Actor player){
        this.player = player;
        lastDirection = -1;
    }

    public int move() {
        int direction = -1;
        Vector2 finalPosition = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            finalPosition.x += 2;
            if(!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)){
                direction = 7;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            finalPosition.x -= 2;
            if(!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)){
                direction = 6;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            finalPosition.y += 2;
            direction = 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            finalPosition.y -= 2;
            direction = 4;
        }
        if(direction == -1){
            switch (lastDirection){
                case 4 -> direction = 0;
                case 5 -> direction = 3;
                case 6 -> direction = 2;
                case 7 -> direction = 1;
            }
        }

        player.setX(player.getX() + finalPosition.x);

        player.setY(player.getY() + finalPosition.y);
        player.getStage().getCamera().translate(finalPosition.x, finalPosition.y, 0);
        lastDirection = direction;
        return direction;
    }
}
