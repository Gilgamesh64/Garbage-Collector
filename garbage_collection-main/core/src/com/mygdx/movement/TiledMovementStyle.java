package com.mygdx.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import java.util.HashSet;
import java.util.Set;

public class TiledMovementStyle extends MovementStyle {

    private final Set<Character> inputs;
    private boolean inputted = false;
    private long lastMove, firstInput;
    private final Actor player;

    public TiledMovementStyle(Actor player) {
        inputs = new HashSet<>();
        lastMove = 0;
        firstInput = 0;
        this.player = player;
    }

    public int move() {
        long sinceLastMove = (Gdx.graphics.getFrameId() - lastMove);

        if (sinceLastMove < 5) return -1;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) inputs.add('W');
        if (Gdx.input.isKeyPressed(Input.Keys.S)) inputs.add('S');
        if (Gdx.input.isKeyPressed(Input.Keys.A)) inputs.add('A');
        if (Gdx.input.isKeyPressed(Input.Keys.D)) inputs.add('D');

        if (sinceLastMove < 9) return -1;

        if (inputs.isEmpty()) return -1;

        if (!inputted) {
            inputted = true;
            firstInput = Gdx.graphics.getFrameId();
            return -1;
        }

        long sinceFirstInput = (Gdx.graphics.getFrameId() - firstInput);

        if (sinceFirstInput > 1) {
            float x = 0, y = 0;

            for (Character c : inputs) {
                switch (c) {
                    case 'W' -> y += 50;
                    case 'A' -> x -= 50;
                    case 'S' -> y -= 50;
                    case 'D' -> x += 50;
                }
            }

            inputs.clear();
            inputted = false;
            lastMove = Gdx.graphics.getFrameId();
            MoveByAction mba = new MoveByAction();
            mba.setAmount(x, y);
            mba.setDuration(0.1f);
            player.addAction(mba);
            int dir = -1;
            if (y == 50) dir = 1;
            else if (y == -50) dir = 0;
            if (x == 50) dir = 3;
            else if (x == -50) dir = 2;
            return dir;
        }
        return -1;
    }
}
