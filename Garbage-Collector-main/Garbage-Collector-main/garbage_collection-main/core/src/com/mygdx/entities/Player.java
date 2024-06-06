package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.movement.MovementStyle;
import com.mygdx.animations.PlayerAnimationManager;
import com.mygdx.movement.RealtimeMovementStyle;
import com.mygdx.movement.TiledMovementStyle;

public class Player extends Actor {

    private final PlayerAnimationManager playerAnimationManager;
    private MovementStyle movementStyle;
    public enum Styles {REALTIME, TILED}

    public Player(int x, int y){

        setX(x);
        setY(y);

        playerAnimationManager = new PlayerAnimationManager();
        setWidth(32);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
    }

    public void setMovementStyle(Styles s) {
        switch (s){
            case REALTIME :
                movementStyle = new RealtimeMovementStyle(this);
                break;
            case TILED :
                movementStyle = new TiledMovementStyle(this);
                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(playerAnimationManager.getCurrentFrame(), getX()+8, getY()+8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    
        playerAnimationManager.setCurrentAnimation(movementStyle.move());
        playerAnimationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }
}