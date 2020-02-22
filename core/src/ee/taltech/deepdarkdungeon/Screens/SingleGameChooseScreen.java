package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.Warrior;
import ee.taltech.deepdarkdungeon.View.GameScreen;

public class SingleGameChooseScreen implements Screen{
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
    private Texture warrior2Texture;
    private SpriteBatch batch;
    private Warrior warrior;
    private Warrior warrior2;
    private Texture backButton;

    public SingleGameChooseScreen(DeepDarkDungeonGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backButton = new Texture(Gdx.files.internal("backbutton.png"));
        background = new Texture(Gdx.files.internal("playerchoosebackgroung.jpg"));
        startButton = new Texture(Gdx.files.internal("startbutton.png"));
        warriorTexture = new Texture(Gdx.files.internal("warrior.png"));
        warrior2Texture = new Texture(Gdx.files.internal("warrior2.png"));
        warrior = new Warrior(warriorTexture, 0, 0, 200, 277);
        warrior2 = new Warrior(warrior2Texture, 200, 0, 200, 277);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0,0);
        batch.draw(startButton, PLAY_BUTTON_START, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        if (Gdx.input.getX() < PLAY_BUTTON_START + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                game.setScreen(new GameScreen());
            }
        }
        batch.draw(backButton, EXIT_BUTTON_START, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        if (Gdx.input.getX() < EXIT_BUTTON_START + EXIT_BUTTON_WIDTH && Gdx.input.getX() > EXIT_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
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
