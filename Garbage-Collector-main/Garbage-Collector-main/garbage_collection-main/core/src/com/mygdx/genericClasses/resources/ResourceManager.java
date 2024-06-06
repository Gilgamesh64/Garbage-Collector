package com.mygdx.genericClasses.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.EnumMap;

public class ResourceManager {

    private final AssetManager manager;

    private final EnumMap<ResourceEnum, String> pathMap;

    public ResourceManager(){
        manager = new AssetManager();
        pathMap = new EnumMap<ResourceEnum, String>(ResourceEnum.class);
        loadPathMap();
        loadTextures();
    }

    private void loadTextures(){
        for(ResourceEnum key : pathMap.keySet()){
            manager.load(pathMap.get(key), Texture.class);
        }
    }

    private void loadPathMap(){
        pathMap.put(ResourceEnum.PLAYER, "animation_sheet.png");
        pathMap.put(ResourceEnum.BACKGROUND, "sc_map.png");

    }

    public Texture getTexture(ResourceEnum e){
        manager.finishLoadingAsset(pathMap.get(e));
        return manager.get(pathMap.get(e));
    }

    public void dispose(){
        manager.dispose();
    }
}
