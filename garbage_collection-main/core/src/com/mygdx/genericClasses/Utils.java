package com.mygdx.genericClasses;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.genericClasses.resources.ResourceEnum;
import com.mygdx.genericClasses.resources.ResourceManager;

public class Utils {

    private static final ResourceManager manager = new ResourceManager();

    public static Texture getTexture(ResourceEnum e){
        return manager.getTexture(e);
    }

    public static void dispose(){
        manager.dispose();
    }
}
