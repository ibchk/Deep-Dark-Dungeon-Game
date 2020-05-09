package ee.taltech.deepdarkdungeon.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AnimationClass {

    public int frameRows;
    public int frameCols;

    public Animation animation;
    public Texture texture;
    public TextureRegion[] textureRegions;
    public TextureRegion currentFrame;

    TextureRegion[][] tmp;
    public float stateTime;

    public AnimationClass(Texture texture, int frameRows, int frameCols) {
        this.texture = texture;
        this.frameRows = frameRows;
        this.frameCols = frameCols;

        tmp = TextureRegion.split(texture, texture.getWidth() / frameCols, texture.getHeight() / frameRows);
        textureRegions = new TextureRegion[frameCols * frameRows];
        int index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                textureRegions[index++] = tmp[i][j];
            }
        }
        animation = new Animation(0.04f, textureRegions);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTime = 0f;
        currentFrame = new TextureRegion();
    }

    public void startAnimation(){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) animation.getKeyFrame(stateTime);
    }
}
