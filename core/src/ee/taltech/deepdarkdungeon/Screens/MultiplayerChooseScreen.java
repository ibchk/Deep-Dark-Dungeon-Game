package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.PutMusic;

public class MultiplayerChooseScreen implements Screen {

    PutMusic music;
    DeepDarkDungeonGame game;
    private Texture background;
    private SpriteBatch batch;

    public MultiplayerChooseScreen(DeepDarkDungeonGame game, PutMusic music) {
        this.game = game;
        this.music = music;
    }

    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("backScreenChooseScreen.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
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

    }
}
