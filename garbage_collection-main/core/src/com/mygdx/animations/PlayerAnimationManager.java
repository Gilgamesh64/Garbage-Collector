package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.entities.Player;
import com.mygdx.genericClasses.Utils;
import com.mygdx.genericClasses.resources.ResourceEnum;

public class PlayerAnimationManager {
    private final Animation<TextureRegion> idleDown;
    private final Animation<TextureRegion> idleRight;
    private final Animation<TextureRegion> idleLeft;
    private final Animation<TextureRegion> idleUp;
    private final Animation<TextureRegion> walkDownAnimation;
    private final Animation<TextureRegion> walkUpAnimation;
    private final Animation<TextureRegion> walkRightAnimation;
    private final Animation<TextureRegion> walkLeftAnimation;

    private Animation<TextureRegion> currentAnimation;
    
    private TextureRegion currentFrame;

    private float stateTime;

    public PlayerAnimationManager(){
        Texture walkSheet = Utils.getTexture(ResourceEnum.PLAYER);

        int FRAME_COLS = 7;
        int FRAME_ROWS = 3;

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] idleDownFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] idleRightFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] idleLeftFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] idleUpFrames = new TextureRegion[1];

        TextureRegion[] walkDownFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkUpFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkRightFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_ROWS];

        idleUpFrames[0] = tmp[0][4];
		for (int i = 0; i < FRAME_ROWS; i++) {
            idleDownFrames[i] = tmp[i][0];
            idleRightFrames[i] = tmp[i][1];
            idleLeftFrames[i] = tmp[i][2];

			walkDownFrames[i] = tmp[i][3];
            walkUpFrames[i] = tmp[i][4];
            walkRightFrames[i] = tmp[i][5];
            walkLeftFrames[i] = tmp[i][6];
        }

        idleDown = new Animation<TextureRegion>(0.3f, idleDownFrames);
        idleRight = new Animation<TextureRegion>(0.3f, idleRightFrames);
        idleLeft = new Animation<TextureRegion>(0.3f, idleLeftFrames);
        idleUp = new Animation<TextureRegion>(0.3f, idleUpFrames);
        walkDownAnimation = new Animation<TextureRegion>(0.2f, walkDownFrames);
        walkUpAnimation = new Animation<TextureRegion>(0.2f, walkUpFrames);
        walkRightAnimation = new Animation<TextureRegion>(0.2f, walkRightFrames);
        walkLeftAnimation = new Animation<TextureRegion>(0.2f, walkLeftFrames);

        currentAnimation = idleDown;

		stateTime = 0f;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }
    public void updateAnimation(float delta){
        stateTime += delta;
        currentFrame = currentAnimation.getKeyFrame(stateTime, true);
    }

    /**
     * @param direction 0: idleDown, 1:idleRight, 2: idleLeft, 3: idleUp, 4: walkDown, 5: walkUp, 6: walkLeft, 7: walkRight
     * */
    public void setCurrentAnimation(int direction){
        switch (direction) {
            case 0 -> currentAnimation = idleDown;
            case 1 -> currentAnimation = idleRight;
            case 2 -> currentAnimation = idleLeft;
            case 3 -> currentAnimation = idleUp;
            case 4 -> currentAnimation = walkDownAnimation;
            case 5 -> currentAnimation = walkUpAnimation;
            case 6 -> currentAnimation = walkLeftAnimation;
            case 7 -> currentAnimation = walkRightAnimation;
        }
    }
}
