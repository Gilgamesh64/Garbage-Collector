package com.mygdx.genericClasses;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileSetManager {
    private final TiledMapRenderer tiledMapRenderer;

    public TileSetManager(){
        tiledMapRenderer = new OrthogonalTiledMapRenderer(new TmxMapLoader().load("tiles.tmx"));
    }

    public void render(OrthographicCamera camera){
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }
}
