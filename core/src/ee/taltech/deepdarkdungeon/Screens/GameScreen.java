package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.Models.GameObject;

import java.util.List;

public class GameScreen implements Screen {

    BitmapFont font = new BitmapFont();
    private Texture background;
    private SpriteBatch batch;
    private GameObject goodCharacter1;
    private GameObject goodCharacter2;
    private GameObject goodCharacter3;
    private GameObject goodCharacter4;
    private GameObject badCharacter1;
    private GameObject badCharacter2;
    private GameObject badCharacter3;
    private GameObject badCharacter4;

    public GameScreen(List<GameObject> goodCharacters, List<GameObject> badCharacters) {
        goodCharacter1 = goodCharacters.get(0);
        goodCharacter2 = goodCharacters.get(1);
        goodCharacter3 = goodCharacters.get(2);
        goodCharacter4 = goodCharacters.get(3);
        badCharacter1 = badCharacters.get(0);
        badCharacter1 = badCharacters.get(1);
        badCharacter1 = badCharacters.get(2);
        badCharacter1 = badCharacters.get(3);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("playerchoosebackgroung.jpg"));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(goodCharacter1.getPicture(), 0, 0); // рисует персанажа (картинка)
        batch.draw(goodCharacter2.getPicture(), 200, 0);
        batch.draw(goodCharacter3.getPicture(), 400, 0);
        batch.draw(goodCharacter4.getPicture(), 600, 0);
        batch.draw(badCharacter1.getPicture(), 1000, 0);
        font.draw(batch, goodCharacter1.getPower() + "", 200, 500); // Вызывает текст, тут например power персанажа
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
    }
}
