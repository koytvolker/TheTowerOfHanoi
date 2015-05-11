package com.example.yo.thetowerofhanoi;

import java.io.IOException;
import java.util.Stack;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;

public class MainActivity extends SimpleBaseGameActivity {

    private static int CAMERA_WIDTH = 1024, CAMERA_HEIGHT = 600, level = 1, numOfRings = 3;
    private ITextureRegion
            mTexRegBgLand,
            mTexRegBgSky,
            mTexRegBgCloud1,
            mTexRegBgCloud2,
            mTexRegBgCloud3,
            mTexRegBgWall,
            mTexRegTower1,
            mTexRegTower2,
            mTexRegTower3;
    private ITextureRegion[] mTexRegRings;
    private ITexture[] mTexRings;
    private Sprite[] mSpriteRings;

    @Override
    public EngineOptions onCreateEngineOptions() {
        // TODO Auto-generated method stub
        //return null;

        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(
                true,
                ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
                camera
        );
    }

    @Override
    protected void onCreateResources() throws IOException {
        // TODO Auto-generated method stub

        final ITexture texBgSky = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/bg-sky.png"
        );
        this.mTexRegBgSky = TextureRegionFactory.extractFromTexture(texBgSky);
        texBgSky.load();

        final ITexture texBgCloud1 = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/bg-cloud.png"
        );
        this.mTexRegBgCloud1 = TextureRegionFactory.extractFromTexture(texBgCloud1);
        texBgCloud1.load();

        final ITexture texBgCloud2 = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/bg-cloud.png"
        );
        this.mTexRegBgCloud2 = TextureRegionFactory.extractFromTexture(texBgCloud2);
        texBgCloud2.load();

        final ITexture texBgCloud3 = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/bg-cloud.png"
        );
        this.mTexRegBgCloud3 = TextureRegionFactory.extractFromTexture(texBgCloud3);
        texBgCloud3.load();

        final ITexture texBgLand = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/bg-land.png"
        );
        this.mTexRegBgLand = TextureRegionFactory.extractFromTexture(texBgLand);
        texBgLand.load();

        final ITexture texTower1 = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/tower.png"
        );
        this.mTexRegTower1 = TextureRegionFactory.extractFromTexture(texTower1);
        texTower1.load();

        final ITexture texTower2 = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/tower.png"
        );
        this.mTexRegTower2 = TextureRegionFactory.extractFromTexture(texTower2);
        texTower2.load();

        final ITexture texTower3 = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/tower.png"
        );
        this.mTexRegTower3 = TextureRegionFactory.extractFromTexture(texTower3);
        texTower3.load();

        final ITexture texBgWall = new AssetBitmapTexture(
                this.getTextureManager(),
                this.getAssets(),
                "gfx/bg-wall.png"
        );
        this.mTexRegBgWall = TextureRegionFactory.extractFromTexture(texBgWall);
        texBgWall.load();

        numOfRings = (level - 1) + 3;
        mTexRegRings = new ITextureRegion[numOfRings];
        mTexRings = new ITexture[numOfRings];
        for(int i = 0; i < numOfRings; i++){
            mTexRings[i] = new AssetBitmapTexture(
                    this.getTextureManager(),
                    this.getAssets(),
                    "gfx/ring.png"
            );
            this.mTexRegRings[i] = TextureRegionFactory.extractFromTexture(mTexRings[i]);
            mTexRings[i].load();
        }
    }

    @Override
    protected Scene onCreateScene() {
        // TODO Auto-generated method stub
        //return null;

        final Scene scene = new Scene();
        //scene.getBackground().setColor(Color.CYAN);

        final float
                centerX = CAMERA_WIDTH / 2,
                centerY = CAMERA_HEIGHT / 2,
                _1stQuarterX = centerX / 2,
                _3rdQuarterX = centerX + (centerX / 2),
                _1stQuarterY = centerY / 2;

        final Sprite spriteBgSky = new Sprite(
                centerX,
                centerY,
                this.mTexRegBgSky,
                this.getVertexBufferObjectManager()
        );
        spriteBgSky.setWidth(CAMERA_WIDTH);
        spriteBgSky.setHeight(CAMERA_HEIGHT);
        scene.attachChild(spriteBgSky);

        final Sprite spriteBgCloud1 = new Sprite(
                CAMERA_WIDTH,
                centerY,
                this.mTexRegBgCloud1,
                this.getVertexBufferObjectManager()
        );
        spriteBgCloud1.setWidth(CAMERA_WIDTH);
        spriteBgCloud1.setHeight(CAMERA_HEIGHT);
        scene.attachChild(spriteBgCloud1);

        final Sprite spriteBgCloud2 = new Sprite(
                centerX,
                centerY,
                this.mTexRegBgCloud2,
                this.getVertexBufferObjectManager()
        );
        spriteBgCloud2.setWidth(CAMERA_WIDTH);
        spriteBgCloud2.setHeight(CAMERA_HEIGHT);
        scene.attachChild(spriteBgCloud2);

        final Sprite spriteBgCloud3 = new Sprite(
                0,
                centerY,
                this.mTexRegBgCloud3,
                this.getVertexBufferObjectManager()
        );
        spriteBgCloud3.setWidth(CAMERA_WIDTH);
        spriteBgCloud3.setHeight(CAMERA_HEIGHT);
        scene.attachChild(spriteBgCloud3);

        final Sprite spriteBgLand = new Sprite(
                centerX,
                centerY,
                this.mTexRegBgLand,
                this.getVertexBufferObjectManager()
        );
        spriteBgLand.setWidth(CAMERA_WIDTH);
        spriteBgLand.setHeight(CAMERA_HEIGHT);
        scene.attachChild(spriteBgLand);

        final Sprite spriteBgWall = new Sprite(
                centerX,
                centerY,
                this.mTexRegBgWall,
                this.getVertexBufferObjectManager()
        );
        spriteBgWall.setWidth(CAMERA_WIDTH);
        spriteBgWall.setHeight(CAMERA_HEIGHT);
        scene.attachChild(spriteBgWall);

        final Sprite spriteTower1 = new Sprite(
                _1stQuarterX,
                (float) (_1stQuarterY + (_1stQuarterY / 1.5) - 1),
                this.mTexRegTower1,
                this.getVertexBufferObjectManager()
        );
        spriteTower1.setHeight(centerY);
        scene.attachChild(spriteTower1);

        final Sprite spriteTower2 = new Sprite(
                centerX,
                (float) (_1stQuarterY + (_1stQuarterY / 1.5) - 1),
                this.mTexRegTower1,
                this.getVertexBufferObjectManager()
        );
        spriteTower2.setHeight(centerY);
        scene.attachChild(spriteTower2);

        final Sprite spriteTower3 = new Sprite(
                _3rdQuarterX,
                (float) (_1stQuarterY + (_1stQuarterY / 1.5) - 1),
                this.mTexRegTower1,
                this.getVertexBufferObjectManager()
        );
        spriteTower3.setHeight(centerY);
        scene.attachChild(spriteTower3);

		/*final Sprite spriteBgWall = new Sprite(
			centerX,
			centerY,
			this.mTexRegBgWall,
			this.getVertexBufferObjectManager()
		);
		spriteBgWall.setWidth(CAMERA_WIDTH);
		spriteBgWall.setHeight(CAMERA_HEIGHT);
		scene.attachChild(spriteBgWall);*/

        final float
                heightTower = spriteTower1.getHeight(),
                widthRing = 200,
                heightRing = (heightTower-20) / numOfRings;
        mSpriteRings = new Sprite[numOfRings];
        for(int i = 0; i < numOfRings; i++){
            mSpriteRings[i] = new Sprite(
                    centerX,
                    (float) ((float) _1stQuarterY - (heightTower / (heightRing * 0.25)) + (i * heightRing)),
                    this.mTexRegRings[i],
                    this.getVertexBufferObjectManager()
            );
            mSpriteRings[i].setWidth(widthRing - (i * (100 / numOfRings)));
            mSpriteRings[i].setHeight(heightRing + 5);
            scene.attachChild(mSpriteRings[i]);
        }

        return scene;
    }

}