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
    private static final int VBOI_X = 850;
    private static final int VBOI_Y = 50;
    private static final int VBOI_HEIGTH = 80;
    private static final int VBOI_WIDTH = 201;

    DeepDarkDungeonGame game;
    boolean canbeattacked = false;
    BitmapFont font = new BitmapFont();
    private long stepCount = 1;
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

    public GameScreen(List<GameObject> goodCharacters, List<GameObject> badCharacters, DeepDarkDungeonGame game) {
        this.game = game;
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
        background = new Texture(Gdx.files.internal("GameBackground.png"));
        vboiButton = new Texture(Gdx.files.internal("buttonVBOI.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(vboiButton, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
        batch.draw(goodCharacter1.getTexture(), 0, 100); // рисует персанажа (картинка)
        batch.draw(goodCharacter2.getTexture(), 200, 100);
        batch.draw(goodCharacter3.getTexture(), 400, 100);
        batch.draw(goodCharacter4.getTexture(), 600, 100);
        batch.draw(badCharacter1.getTexture(), 1100, 100);
        batch.draw(badCharacter2.getTexture(), 1300, 100);
        batch.draw(badCharacter3.getTexture(), 1500, 100);
        batch.draw(badCharacter4.getTexture(), 1700, 100);
        font.draw(batch, badCharacter1.getHealth() + "", 1200, 400);
        font.draw(batch, badCharacter2.getHealth() + "", 1400, 400);
        font.draw(batch, badCharacter3.getHealth() + "", 1600, 400);
        font.draw(batch, badCharacter4.getHealth() + "", 1800, 400);
        //Gdx.input.getX() < PLAY_BUTTON_START + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y)
        if (stepCount % 2 == 1) {
            font.draw(batch, "Your turn!" + stepCount, 100, 1000); // Вызывает текст, тут например power персанажа
            if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y + VBOI_HEIGTH && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    batch.draw(goodCharacter2.getTexture(), 200, 800);
                    canbeattacked = true;
                }
            }
        } else if (stepCount % 2 == 0) {
            font.draw(batch, "Monsters turn!" + stepCount, 100, 900);// Вызывает текст, тут например power персанажа
            // AI logic
            stepCount += 1;
        }
        if (Gdx.input.getX() > badCharacter1.getX() && Gdx.input.getX() < badCharacter1.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter1.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter1.getY() + 300) {
            batch.draw(goodCharacter1.getTexture(), 200, 500);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked) {
                badCharacter1.setHealth(badCharacter1.getHealth() - goodCharacter1.getPower());
                stepCount += 1;
                canbeattacked = false;
            }
        }
        if (Gdx.input.getX() > badCharacter2.getX() && Gdx.input.getX() < badCharacter2.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter2.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter2.getY() + 300) {
            batch.draw(goodCharacter2.getTexture(), 200, 500);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked) {
                badCharacter2.setHealth(badCharacter2.getHealth() - goodCharacter1.getPower());
                stepCount += 1;
                canbeattacked = false;
            }
        }
        if (Gdx.input.getX() > badCharacter3.getX() && Gdx.input.getX() < badCharacter3.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter3.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter3.getY() + 300) {
            batch.draw(goodCharacter3.getTexture(), 200, 500);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked) {
                badCharacter3.setHealth(badCharacter3.getHealth() - goodCharacter1.getPower());
                stepCount += 1;
                canbeattacked = false;
            }
        }
        if (Gdx.input.getX() > badCharacter4.getX() && Gdx.input.getX() < badCharacter4.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter4.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter4.getY() + 300) {
            batch.draw(goodCharacter4.getTexture(), 200, 500);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked) {
                badCharacter4.setHealth(badCharacter4.getHealth() - goodCharacter1.getPower());
                stepCount += 1;
                canbeattacked = false;
            }
        }
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
