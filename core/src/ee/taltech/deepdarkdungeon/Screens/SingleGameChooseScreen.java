package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.*;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonWarrior;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;

import java.util.Arrays;
import java.util.List;

public class SingleGameChooseScreen implements Screen {
    private static final int PLAY_BUTTON_WIDTH = 330;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 100;
    private static final int EXIT_BUTTON_START = 200;
    private static final int PLAY_BUTTON_START = 1400;


    DeepDarkDungeonGame game;
    private Texture startButton;
    private Texture background;
    private Texture warriorTexture;
    private SpriteBatch batch;
    private Texture backButton;

    public SingleGameChooseScreen(DeepDarkDungeonGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backButton = new Texture(Gdx.files.internal("backbutton.png"));
        background = new Texture(Gdx.files.internal("playerchoosebackgroung.jpg"));
        startButton = new Texture(Gdx.files.internal("startbutton.png"));
        warriorTexture = new Texture(Gdx.files.internal("warrior.png"));
    }

    //Далее я создаю персонажей чтобы закинуть их в игру, это нужно будет структурировать,
    // так как мы не знаем какие персонажи будут выбраны и сколько их будет и в какой последовательности.
    GameObject goodCharacter1 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Char1",  100, 1000, 0, 0, 200, 277,  GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD3);
    GameObject goodCharacter2 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Char2", 100, 1000, 200, 0, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD3);
    GameObject goodCharacter3 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Char3", 100, 1000, 400, 0, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD3);
    GameObject goodCharacter4 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Char4", 100, 1000, 600, 0, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD4);
    GameObject badCharacter1 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter1.png")), "BadChar1", 100, 1000, 1000, 0, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD1);
    GameObject badCharacter2 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter1.png")), "BadChar2", 100, 1000, 1200, 0, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD1);
    GameObject badCharacter3 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter1.png")), "BadChar3",100, 1000, 1400, 0, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD1);
    GameObject badCharacter4 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter1.png")), "BadChar4", 100, 1000, 1600, 0, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD1);

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(startButton, PLAY_BUTTON_START, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        if (Gdx.input.getX() < PLAY_BUTTON_START + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new GameScreen(Arrays.asList(goodCharacter1, goodCharacter2,
                        goodCharacter3, goodCharacter4), Arrays.asList(badCharacter1,
                        badCharacter2, badCharacter3, badCharacter4), game)); // Тут я закидываю два листа и персонажами которые будут в игре. первый лист
            }
        }
        batch.draw(backButton, EXIT_BUTTON_START, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        if (Gdx.input.getX() < EXIT_BUTTON_START + EXIT_BUTTON_WIDTH && Gdx.input.getX() > EXIT_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
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