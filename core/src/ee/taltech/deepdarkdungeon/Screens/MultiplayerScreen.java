package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryonet.Client;
import ee.taltech.deepdarkdungeon.Client.MPClient;
import ee.taltech.deepdarkdungeon.Models.GameObject;

import java.util.LinkedList;
import java.util.List;

public class MultiplayerScreen implements Screen {


    private SpriteBatch batch;
    BitmapFont font = new BitmapFont();
    private Texture background;
    private boolean write = true;
    private List<GameObject> chars1;
    private List<String> chars2;
    private MPClient client;

    public MultiplayerScreen(List<GameObject> chars1) {
        this.chars1 = chars1;
        List<String> heroNames = new LinkedList<>();
        for (GameObject hero : chars1) {
            heroNames.add(hero.name);
        }
        this.client = new MPClient(heroNames);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("backScreenChooseScreen.png"));
    }

    @Override
    public void render(float delta) {
        if (client.game) {
            if (write) {
                this.chars2 = client.enemy;
                System.out.println(chars2);
                System.out.println(chars1);
                write = false;
            }
            Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0, 0);
            batch.end();
        }
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
