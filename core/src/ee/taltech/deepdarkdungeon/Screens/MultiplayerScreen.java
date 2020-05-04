package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.Client.MPClient;
import ee.taltech.deepdarkdungeon.Models.GameObject;

import java.util.List;

public class MultiplayerScreen implements Screen {

    private SpriteBatch batch;
    BitmapFont font = new BitmapFont();
    private Texture background;

    private List<GameObject> chars1;
    private List<String> chars2;
    private MPClient client;

    public MultiplayerScreen(MPClient client, List<GameObject> chars1) {
        this.chars1 = chars1;
        this.client = client;
        this.chars2 = client.enemy;
        System.out.println(chars1);
        System.out.println(chars2);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("backScreenChooseScreen.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
