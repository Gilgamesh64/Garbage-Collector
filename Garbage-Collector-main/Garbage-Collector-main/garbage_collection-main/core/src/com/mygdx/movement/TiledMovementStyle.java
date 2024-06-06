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
    private String lastDirection; 

    public TiledMovementStyle(Actor player) {
        inputs = new HashSet<>();
        lastMove = 0;
        firstInput = 0;
        this.player = player;
        lastDirection = "-";
    }
    public String move() {
        long sinceLastMove = (Gdx.graphics.getFrameId() - lastMove);

        if (sinceLastMove < 5) return "";

        if (Gdx.input.isKeyPressed(Input.Keys.W)) inputs.add('W');
        if (Gdx.input.isKeyPressed(Input.Keys.S)) inputs.add('S');
        if (Gdx.input.isKeyPressed(Input.Keys.A)) inputs.add('A');
        if (Gdx.input.isKeyPressed(Input.Keys.D)) inputs.add('D');

        if (sinceLastMove < 9) return "";

        if (inputs.isEmpty()) return lastDirection;

        if (!inputted) {
            inputted = true;
            firstInput = Gdx.graphics.getFrameId();
            return "";
        }

        long sinceFirstInput = (Gdx.graphics.getFrameId() - firstInput);

        if (sinceFirstInput > 1) {
            float x = 0, y = 0;

            for (Character c : inputs) {
                switch (c) {
                    case 'W' -> y += 32;
                    case 'A' -> x -= 32;
                    case 'S' -> y -= 32;
                    case 'D' -> x += 32;
                }
            }

            inputs.clear();
            inputted = false;
            lastMove = Gdx.graphics.getFrameId();
            MoveByAction mba = new MoveByAction();
            mba.setAmount(x, y);
            mba.setDuration(0.1f);
            player.addAction(mba);
            String dir = "-";
            if (y == 32) dir = "wW";
            else if (y == -32) dir = "wS";
            if (x == 32) dir = "wD";
            else if (x == -32) dir = "wA";
            lastDirection = "i" + dir.substring(1);
            return dir;
        }
        return "";
    }
}
