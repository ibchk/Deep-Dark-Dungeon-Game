package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.*;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonWarrior;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;

import java.util.*;

public class SingleGameChooseScreen implements Screen {
    private static final int PLAY_BUTTON_WIDTH = 250;
    private static final int PLAY_BUTTON_HEIGHT = 78;
    private static final int PLAY_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_START = 1400;
    private static final int BACK_BUTTON_WIDTH = 206;
    private static final int BACK_BUTTON_HEIGHT = 78;
    private static final int BACK_BUTTON_Y = 100;
    private static final int BACK_BUTTON_START = 200;

    DeepDarkDungeonGame game;
    Stage stage;
    private Texture startButton;
    private Texture startButton2;
    private Texture background;
    private Texture warriorTexture;
    private SpriteBatch batch;
    private Texture backButton;
    private Texture backButton2;
    private Texture previousCharacterButton;
    private Texture previousCharacterButton2;
    private Texture nextCharacterButton;
    private Texture nextCharacterButton2;
    GameObject goodCharacter1;
    GameObject goodCharacter2;
    GameObject goodCharacter3;
    GameObject goodCharacter4;
    GameObject badCharacter1;
    GameObject badCharacter2;
    GameObject badCharacter3;
    GameObject badCharacter4;
    List<GameObject> characters = new ArrayList<>();
    int neededCharacter1 = 0;
    int neededCharacter2 = 0;
    int neededCharacter3 = 0;
    int neededCharacter4 = 0;
    BitmapFont font = new BitmapFont();

    public SingleGameChooseScreen(DeepDarkDungeonGame game) {
        this.game = game;
        goodCharacter1 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter1.png")), "Char1", 100, 1000, 0, 450, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD1);
        goodCharacter2 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Char2", 100, 1000, 200, 400, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD2);
        goodCharacter3 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter3.png")), "Char3", 100, 1000, 400, 450, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD3);
        goodCharacter4 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter4.png")), "Char4", 100, 1000, 600, 400, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD4);
        badCharacter1 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter1.png")), "BadChar1", 100, 1000, 1100, 450, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD1);
        badCharacter2 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter2.png")), "BadChar2", 100, 1000, 1300, 400, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD2);
        badCharacter3 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter3.png")), "BadChar3", 100, 1000, 1500, 450, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD3);
        badCharacter4 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter4.png")), "BadChar4", 100, 1000, 1700, 400, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD4);
        characters.add(goodCharacter1);
        characters.add(goodCharacter2);
        characters.add(goodCharacter3);
        characters.add(goodCharacter4);
    }

    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("playerchoosebackgroung.jpg"));
        batch = new SpriteBatch();
        backButton = new Texture(Gdx.files.internal("backbutton.png"));
        backButton2 = new Texture(Gdx.files.internal("backbutton2.png"));
        startButton = new Texture(Gdx.files.internal("startbutton.png"));
        startButton2 = new Texture(Gdx.files.internal("startbutton2.png"));
        warriorTexture = new Texture(Gdx.files.internal("warrior.png"));
        previousCharacterButton = new Texture(Gdx.files.internal("previousCharacterButton.png"));
        previousCharacterButton2 = new Texture(Gdx.files.internal("previousCharacterButton2.png"));
        nextCharacterButton = new Texture(Gdx.files.internal("nextCharacterButton.png"));
        nextCharacterButton2 = new Texture(Gdx.files.internal("nextCharacterButton2.png"));

    }

    //Далее я создаю персонажей чтобы закинуть их в игру, это нужно будет структурировать,
    // так как мы не знаем какие персонажи будут выбраны и сколько их будет и в какой последовательности.
    @Override
    public void render(float delta) {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();

        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);

        // First char choose start:
        batch.draw(characters.get(neededCharacter1).getTexture(), 284, 600, 200, 222);
        batch.draw(nextCharacterButton, 500, 670, 45, 63);
        batch.draw(previousCharacterButton, 215, 670, 45, 63);
        if (Gdx.input.getX() < 500 + 45 && Gdx.input.getX() > 500 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(nextCharacterButton2, 500, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter1++;
                if (neededCharacter1 == 4) {
                    neededCharacter1 = 0;
                }
                batch.draw(characters.get(neededCharacter1).getTexture(), 284, 600, 200, 222);
            }
        }
        if (Gdx.input.getX() < 215 + 45 && Gdx.input.getX() > 215 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(previousCharacterButton2, 215, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter1 == 0) {
                    neededCharacter1 = 4;
                }
                neededCharacter1--;
                batch.draw(characters.get(neededCharacter1).getTexture(), 284, 600, 200, 222);
            }
        } // First character choose ended.

        // Second char choose start:
        batch.draw(characters.get(neededCharacter2).getTexture(), 668, 600, 200, 222);
        batch.draw(nextCharacterButton, 884, 670, 45, 63);
        batch.draw(previousCharacterButton, 599, 670, 45, 63);
        if (Gdx.input.getX() < 884 + 45 && Gdx.input.getX() > 884 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(nextCharacterButton2, 884, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter2++;
                if (neededCharacter2 == 4) {
                    neededCharacter2 = 0;
                }
                batch.draw(characters.get(neededCharacter2).getTexture(), 668, 600, 200, 222);
            }
        }
        if (Gdx.input.getX() < 599 + 45 && Gdx.input.getX() > 599 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(previousCharacterButton2, 599, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter2 == 0) {
                    neededCharacter2 = 4;
                }
                neededCharacter2--;
                batch.draw(characters.get(neededCharacter2).getTexture(), 668, 600, 200, 222);
            }
        } // Second character choose ended.

        // Third char choose start:
        batch.draw(characters.get(neededCharacter3).getTexture(), 1052, 600, 200, 222);
        batch.draw(nextCharacterButton, 1268, 670, 45, 63);
        batch.draw(previousCharacterButton, 983, 670, 45, 63);
        if (Gdx.input.getX() < 1268 + 45 && Gdx.input.getX() > 1268 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(nextCharacterButton2, 1268, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter3++;
                if (neededCharacter3 == 4) {
                    neededCharacter3 = 0;
                }
                batch.draw(characters.get(neededCharacter3).getTexture(), 1052, 600, 200, 222);
            }
        }
        if (Gdx.input.getX() < 983 + 45 && Gdx.input.getX() > 983 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(previousCharacterButton2, 983, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter3 == 0) {
                    neededCharacter3 = 4;
                }
                neededCharacter3--;
                batch.draw(characters.get(neededCharacter3).getTexture(), 1052, 600, 200, 222);
            }
        } // Third character choose ended.

        // Forth char choose start:
        batch.draw(characters.get(neededCharacter4).getTexture(), 1436, 600, 200, 222);
        batch.draw(nextCharacterButton, 1652, 670, 45, 63);
        batch.draw(previousCharacterButton, 1367, 670, 45, 63);
        if (Gdx.input.getX() < 1652 + 45 && Gdx.input.getX() > 1652 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(nextCharacterButton2, 1652, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter4++;
                if (neededCharacter4 == 4) {
                    neededCharacter4 = 0;
                }
                batch.draw(characters.get(neededCharacter4).getTexture(), 1436, 600, 200, 222);
            }
        }
        if (Gdx.input.getX() < 1367 + 45 && Gdx.input.getX() > 1367 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < 670 + 63 + 34 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > 670) {
            batch.draw(previousCharacterButton2, 1367, 670, 45, 63);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter4 == 0) {
                    neededCharacter4 = 4;
                }
                neededCharacter4--;
                batch.draw(characters.get(neededCharacter4).getTexture(), 1436, 600, 200, 222);
            }
        } // Forth character choose ended.

        //Game start button:
        batch.draw(startButton, PLAY_BUTTON_START, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        if (Gdx.input.getX() < PLAY_BUTTON_START + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            batch.draw(startButton2, PLAY_BUTTON_START, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new GameScreen(Arrays.asList(characters.get(neededCharacter1 % 4)
                        , characters.get(neededCharacter2), characters.get(neededCharacter3 % 4)
                        , characters.get(neededCharacter4))
                        , Arrays.asList(badCharacter1, badCharacter2, badCharacter3, badCharacter4), game));
                // Тут я закидываю два листа и персонажами которые будут в игре. первый лист
            }
        }

        //back button:
        batch.draw(backButton, BACK_BUTTON_START, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        if (Gdx.input.getX() < BACK_BUTTON_START + BACK_BUTTON_WIDTH && Gdx.input.getX() > BACK_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BACK_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
            batch.draw(backButton2, BACK_BUTTON_START, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new MainMenuScreen(game));
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
        warriorTexture.dispose();
        batch.dispose();
    }
}
