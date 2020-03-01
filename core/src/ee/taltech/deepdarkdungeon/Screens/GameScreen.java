package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.GameObject;

import java.util.List;

public class GameScreen implements Screen {

    BitmapFont font = new BitmapFont();
    DeepDarkDungeonGame game;
    private boolean attackispressed = false;
    private Texture vboiButton;
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
        badCharacter2 = badCharacters.get(1);
        badCharacter3 = badCharacters.get(2);
        badCharacter4 = badCharacters.get(3);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("playerchoosebackgroung.jpg"));
        vboiButton = new Texture(Gdx.files.internal("buttonVBOI.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(vboiButton, 850, 50);
        if (Gdx.input.getX() < 850 + 152 && Gdx.input.getX() > 850 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 50 + 43  && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 50) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !attackispressed) {
                attackispressed = true;
            } else if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && attackispressed) {
                attackispressed = false;
            }
        }
        batch.draw(goodCharacter1.getTexture(), 0, 100); // рисует персанажа (картинка)
        batch.draw(goodCharacter2.getTexture(), 200, 100);
        batch.draw(goodCharacter3.getTexture(), 400, 100);
        batch.draw(goodCharacter4.getTexture(), 600, 100);
        batch.draw(badCharacter1.getTexture(), 1100, 100);
        batch.draw(badCharacter2.getTexture(), 1300, 100);
        batch.draw(badCharacter3.getTexture(), 1500, 100);
        batch.draw(badCharacter4.getTexture(), 1700, 100);
        font.draw(batch, badCharacter1.getHealth() + "", 1200, 450); // Вызывает текст, тут например power персанажа
        font.draw(batch, badCharacter2.getHealth() + "", 1400, 450);
        font.draw(batch, badCharacter3.getHealth() + "", 1600, 450);
        font.draw(batch, badCharacter4.getHealth() + "", 1800, 450);
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
