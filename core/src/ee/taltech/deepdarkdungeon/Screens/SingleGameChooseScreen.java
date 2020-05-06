package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ee.taltech.deepdarkdungeon.Client.MPClient;
import ee.taltech.deepdarkdungeon.Client.Packets;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.*;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Necromancer;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonArcher;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonWarrior;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Zombie;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Archer;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Magic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Paladin;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;
import ee.taltech.deepdarkdungeon.Models.CharacterCreating;

import java.util.*;

public class SingleGameChooseScreen implements Screen {
    private static final int PLAY_BUTTON_WIDTH = 250;
    private static final int PLAY_BUTTON_HEIGHT = 78;
    private static final int PLAY_BUTTON_Y_START = 870;
    private static final int PLAY_BUTTON_X_START = 1470;
    private static final int PLAY_BUTTON_Y_END = PLAY_BUTTON_Y_START + PLAY_BUTTON_HEIGHT;
    private static final int PLAY_BUTTON_X_END = PLAY_BUTTON_X_START + PLAY_BUTTON_WIDTH;
    private static final int PLAYBUTTON_Y_FORBUTTONCHANGE = 100;

    private static final int BACK_BUTTON_WIDTH = 206;
    private static final int BACK_BUTTON_HEIGHT = 78;
    private static final int BACK_BUTTON_Y_START = 870;
    private static final int BACK_BUTTON_X_START = 200;
    private static final int BACK_BUTTON_Y_END = BACK_BUTTON_Y_START + BACK_BUTTON_HEIGHT;
    private static final int BACK_BUTTON_X_END = BACK_BUTTON_X_START + BACK_BUTTON_WIDTH;
    private static final int BACKBUTTON_Y_FORBUTTONCHANGE = 100;

    private static final int FIRSTCHAR_X = 284;
    private static final int FIRSTCHAR_Y = 700;
    private static final int FIRSTCHAR_WIDTH = 200;
    private static final int FIRSTCHAR_HEIGHT = 222;
    private static final int FIRSTCHAR_NEXTBUTTON_X = 500;
    private static final int FIRSTCHAR_BACKBUTTON_X = 215;
    private static final int FIRSTCHAR_BUTTON_Y = 770;
    private static final int FIRSTCHAR_BUTTON_WIDTH = 45;
    private static final int FIRSTCHAR_BUTTON_HEIGHT = 63;
    private static final int FIRSTCHAR_GETXSTART = 500;
    private static final int FIRSTCHAR_GETXEND = 545;
    private static final int FIRSTCHAR_GETYSTART = 800;
    private static final int FIRSTCHAR_GETYEND = 867;
    private static final int FIRSTCHAR_GETXSTART2 = 215;
    private static final int FIRSTCHAR_GETXEND2 = 260;

    private static final int SECONDCHAR_X = 668;
    private static final int SECONDCHAR_Y = 700;
    private static final int SECONDCHAR_WIDTH = 200;
    private static final int SECONDCHAR_HEIGHT = 222;
    private static final int SECONDCHAR_NEXTBUTTON_X = 884;
    private static final int SECONDCHAR_BACKBUTTON_X = 599;
    private static final int SECONDCHAR_BUTTON_Y = 770;
    private static final int SECONDCHAR_BUTTON_WIDTH = 45;
    private static final int SECONDCHAR_BUTTON_HEIGHT = 63;
    private static final int SECONDCHAR_GETXSTART = 884;
    private static final int SECONDCHAR_GETXEND = 929;
    private static final int SECONDCHAR_GETYSTART = 800;
    private static final int SECONDCHAR_GETYEND = 867;
    private static final int SECONDCHAR_GETXSTART2 = 599;
    private static final int SECONDCHAR_GETXEND2 = 644;

    private static final int THIRDCHAR_X = 1052;
    private static final int THIRDCHAR_Y = 700;
    private static final int THIRDCHAR_WIDTH = 200;
    private static final int THIRDCHAR_HEIGHT = 222;
    private static final int THIRDCHAR_NEXTBUTTON_X = 1268;
    private static final int THIRDCHAR_BACKBUTTON_X = 983;
    private static final int THIRDCHAR_BUTTON_Y = 770;
    private static final int THIRDCHAR_BUTTON_WIDTH = 45;
    private static final int THIRDCHAR_BUTTON_HEIGHT = 63;
    private static final int THIRDCHAR_GETXSTART = 1268;
    private static final int THIRDCHAR_GETXEND = 1313;
    private static final int THIRDCHAR_GETYSTART = 800;
    private static final int THIRDCHAR_GETYEND = 867;
    private static final int THIRDCHAR_GETXSTART2 = 983;
    private static final int THIRDCHAR_GETXEND2 = 1028;

    private static final int FORTHCHAR_X = 1436;
    private static final int FORTHCHAR_Y = 700;
    private static final int FORTHCHAR_WIDTH = 200;
    private static final int FORTHCHAR_HEIGHT = 222;
    private static final int FORTHCHAR_NEXTBUTTON_X = 1652;
    private static final int FORTHCHAR_BACKBUTTON_X = 1367;
    private static final int FORTHCHAR_BUTTON_Y = 770;
    private static final int FORTHCHAR_BUTTON_WIDTH = 45;
    private static final int FORTHCHAR_BUTTON_HEIGHT = 63;
    private static final int FORTHCHAR_GETXSTART = 1652;
    private static final int FORTHCHAR_GETXEND = 1697;
    private static final int FORTHCHAR_GETYSTART = 800;
    private static final int FORTHCHAR_GETYEND = 867;
    private static final int FORTHCHAR_GETXSTART2 = 1367;
    private static final int FORTHCHAR_GETXEND2 = 1412;

    private static final int INFOWINDOW_START_X = 480;
    private static final int INFOWINDOW_START_Y = 270;
    private static final int INFOWINDOW_WIDTH = 920;
    private static final int INFOWINDOW_HEIGHT = 540;

    DeepDarkDungeonGame game;
    int openLevelNumber;
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
    List<GameObject> rightBadCharacters = new ArrayList<>();
    int neededCharacter1 = 0;
    int neededCharacter2 = 0;
    int neededCharacter3 = 0;
    int neededCharacter4 = 0;
    int neededBadCharacter1 = -101;
    int neededBadCharacter2;
    int neededBadCharacter3;
    int neededBadCharacter4;
    BitmapFont font = new BitmapFont();
    PutMusic music;
    Preferences prefs = Gdx.app.getPreferences("my-preferences");
    List<GameObject> badCharacters;
    private boolean infoWind = false;
    private GameObject objectForInfo;
    boolean singleGame;


    private Texture levelButtonLight;
    int levelButtonLight_X = 0;
    int levelButtonLight_Y = 0;
    private int lvlPlaying;
    private Texture level1Button1;
    private Texture level1Button2;
    private Texture level2Button1;
    private Texture level2Button2;
    private Texture level2ButtonLocked;
    private Texture level3Button1;
    private Texture level3Button2;
    private Texture level3ButtonLocked;
    private Texture level4Button1;
    private Texture level4Button2;
    private Texture level4ButtonLocked;
    private Texture level5Button1;
    private Texture level5Button2;
    private Texture level5ButtonLocked;
    private Texture infoButton1;
    private Texture infoButton2;
    private Texture closeButton1;
    private Texture closeButton2;
    private Texture infoWindow;

    public SingleGameChooseScreen(DeepDarkDungeonGame game, int openLevelNumber, PutMusic music, boolean singleGame) {
        this.openLevelNumber = openLevelNumber;
        this.music = music;
        this.game = game;
        this.singleGame = singleGame;
        goodCharacter1 = new Warrior(new Texture(Gdx.files.internal("GoodCharacter1.png")), "Warrior", 100, 100, 0, 400, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD1, 1);
        goodCharacter2 = new Archer(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Archer", 100, 100, 200, 450, 200, 277, GameObject.CharacterClass.ARCHER, GameObject.CharacterType.GOOD2, 2);
        goodCharacter3 = new Magic(new Texture(Gdx.files.internal("GoodCharacter3.png")), "Wizard", 200, 100, 400, 400, 200, 277, GameObject.CharacterClass.MAGIC, GameObject.CharacterType.GOOD3, 3);
        goodCharacter4 = new Paladin(new Texture(Gdx.files.internal("GoodCharacter4.png")), "Paladin", 100, 100, 600, 450, 200, 277, GameObject.CharacterClass.PALADIN, GameObject.CharacterType.GOOD4, 4);
        badCharacter1 = new Necromancer(new Texture(Gdx.files.internal("BadCharacter1.png")), "Necromancer", 100, 10, 1100, 450, 200, 277, GameObject.BadCharacterClass.NECROMANCER, GameObject.CharacterType.BAD1, 100);
        badCharacter2 = new Zombie(new Texture(Gdx.files.internal("BadCharacter2.png")), "Zombie", 100, 10, 1300, 400, 200, 277, GameObject.BadCharacterClass.ZOMBIE, GameObject.CharacterType.BAD2);
        badCharacter3 = new SkeletonWarrior(new Texture(Gdx.files.internal("BadCharacter3.png")), "Skeleton Warrior", 100, 10, 1500, 450, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD3);
        badCharacter4 = new SkeletonArcher(new Texture(Gdx.files.internal("BadCharacter4.png")), "Skeleton Archer", 100, 10, 1700, 400, 200, 277, GameObject.BadCharacterClass.SKELETON_ARCHER, GameObject.CharacterType.BAD4);
        characters.add(goodCharacter1);
        characters.add(goodCharacter2);
        characters.add(goodCharacter3);
        characters.add(goodCharacter4);
        rightBadCharacters.add(badCharacter1);
        rightBadCharacters.add(badCharacter2);
        rightBadCharacters.add(badCharacter3);
        rightBadCharacters.add(badCharacter4);

        for (int i = 1; i <= openLevelNumber; i++) {
            prefs.putBoolean("level" + i, true); // counts open lvls.
        }
    }

    @Override
    public void show() {
        if (singleGame) {
            background = new Texture(Gdx.files.internal("backScreenChooseScreen.png"));
        } else {
            background = new Texture(Gdx.files.internal("backScreenChooseScreenMP.png"));
        }
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
        levelButtonLight = new Texture(Gdx.files.internal("levelButtonLight.png"));
        level1Button1 = new Texture(Gdx.files.internal("level1Button.png"));
        level1Button2 = new Texture(Gdx.files.internal("level1Button2.png"));
        level2Button1 = new Texture(Gdx.files.internal("level2Button.png"));
        level2Button2 = new Texture(Gdx.files.internal("level2Button2.png"));
        level2ButtonLocked = new Texture(Gdx.files.internal("level2ButtonLocked.png"));
        level3Button1 = new Texture(Gdx.files.internal("level3Button1.png"));
        level3Button2 = new Texture(Gdx.files.internal("level3Button2.png"));
        level3ButtonLocked = new Texture(Gdx.files.internal("level3ButtonLocked.png"));
        level4Button1 = new Texture(Gdx.files.internal("level4Button1.png"));
        level4Button2 = new Texture(Gdx.files.internal("level4Button2.png"));
        level4ButtonLocked = new Texture(Gdx.files.internal("level4ButtonLocked.png"));
        level5Button1 = new Texture(Gdx.files.internal("level5Button1.png"));
        level5Button2 = new Texture(Gdx.files.internal("level5Button2.png"));
        level5ButtonLocked = new Texture(Gdx.files.internal("level5ButtonLocked.png"));
        infoButton1 = new Texture(Gdx.files.internal("info1.png"));
        infoButton2 = new Texture(Gdx.files.internal("info2.png"));
        closeButton1 = new Texture(Gdx.files.internal("closeButton1.png"));
        closeButton2 = new Texture(Gdx.files.internal("closeButton2.png"));
        infoWindow = new Texture(Gdx.files.internal("emptyWindow.png"));
    }

    //Далее я создаю персонажей чтобы закинуть их в игру, это нужно будет структурировать,
    // так как мы не знаем какие персонажи будут выбраны и сколько их будет и в какой последовательности.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);

        // Make backlight to the selected level:
        if (!infoWind && levelButtonLight_X != 0) {
            batch.draw(levelButtonLight, levelButtonLight_X, levelButtonLight_Y, 103, 111);
        }

        // First lvl button and it's moves:
        if (singleGame && !infoWind && Gdx.input.getX() < 635 + 90 && Gdx.input.getX() > 635 && 663 < Gdx.input.getY() && Gdx.input.getY() < 760) {
            batch.draw(level1Button2, 635, 290, 90, 97);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededBadCharacter1 = 1;
                neededBadCharacter2 = 1;
                neededBadCharacter3 = 1;
                neededBadCharacter4 = 1;
                levelButtonLight_X = 628;
                levelButtonLight_Y = 283;
                lvlPlaying = 1;
            }
        } else if (singleGame) {
            batch.draw(level1Button1, 635, 290, 90, 97);
        }

        // Second lvl button and it's moves:
        if (singleGame && !infoWind && prefs.getBoolean("level2")) {
            batch.draw(level2Button1, 775, 290, 90, 97);
            if (Gdx.input.getX() < 775 + 90 && Gdx.input.getX() > 775 && 663 < Gdx.input.getY() && Gdx.input.getY() < 760) {
                batch.draw(level2Button2, 775, 290, 90, 97);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    neededBadCharacter1 = 1;
                    neededBadCharacter2 = 1;
                    neededBadCharacter3 = 1;
                    neededBadCharacter4 = 1;
                    levelButtonLight_X = 768;
                    levelButtonLight_Y = 283;
                    lvlPlaying = 2;
                }
            }
        } else if (singleGame) {
            batch.draw(level2ButtonLocked, 775, 290, 90, 97);
        }

        // Third lvl button and it's moves:
        if (singleGame && !infoWind && prefs.getBoolean("level3")) {
            batch.draw(level3Button1, 915, 290, 90, 97);
            if (Gdx.input.getX() < 915 + 90 && Gdx.input.getX() > 915 && 663 < Gdx.input.getY() && Gdx.input.getY() < 760) {
                batch.draw(level3Button2, 915, 290, 90, 97);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    neededBadCharacter1 = 0;
                    neededBadCharacter2 = 1;
                    neededBadCharacter3 = 2;
                    neededBadCharacter4 = 3;
                    levelButtonLight_X = 908;
                    levelButtonLight_Y = 283;
                    lvlPlaying = 3;
                }
            }
        } else if (singleGame) {
            batch.draw(level3ButtonLocked, 915, 290, 90, 97);
        }

        // Forth lvl button and it's moves:
        if (singleGame && !infoWind && prefs.getBoolean("level4")) {
            batch.draw(level4Button1, 1055, 290, 90, 97);
            if (Gdx.input.getX() < 1055 + 90 && Gdx.input.getX() > 1055 && 663 < Gdx.input.getY() && Gdx.input.getY() < 760) {
                batch.draw(level4Button2, 1055, 290, 90, 97);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    neededBadCharacter1 = 2;
                    neededBadCharacter2 = 2;
                    neededBadCharacter3 = 2;
                    neededBadCharacter4 = 2;
                    levelButtonLight_X = 1048;
                    levelButtonLight_Y = 283;
                    lvlPlaying = 4;
                }
            }
        } else if (singleGame) {
            batch.draw(level4ButtonLocked, 1055, 290, 90, 97);
        }

        // Fifth lvl button and it's moves:
        if (singleGame && !infoWind && prefs.getBoolean("level5")) {
            batch.draw(level5Button1, 1195, 290, 90, 97);
            if (Gdx.input.getX() < 1195 + 90 && Gdx.input.getX() > 1195 && 663 < Gdx.input.getY() && Gdx.input.getY() < 760) {
                batch.draw(level5Button2, 1195, 290, 90, 97);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    neededBadCharacter1 = 0;
                    neededBadCharacter2 = 0;
                    neededBadCharacter3 = 0;
                    neededBadCharacter4 = 0;
                    levelButtonLight_X = 1188;
                    levelButtonLight_Y = 283;
                    lvlPlaying = 5;
                }
            }
        } else if (singleGame) {
            batch.draw(level5ButtonLocked, 1195, 290, 90, 97);
        }

        // First char choose start:
        batch.draw(infoButton1, FIRSTCHAR_NEXTBUTTON_X, FIRSTCHAR_BUTTON_Y + 110, FIRSTCHAR_BUTTON_WIDTH, FIRSTCHAR_BUTTON_WIDTH);
        if (!infoWind && Gdx.input.getX() > FIRSTCHAR_NEXTBUTTON_X && Gdx.input.getX() < FIRSTCHAR_NEXTBUTTON_X + FIRSTCHAR_BUTTON_WIDTH && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - FIRSTCHAR_BUTTON_Y - 190 && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - FIRSTCHAR_BUTTON_Y - 140) {
            batch.draw(infoButton2, FIRSTCHAR_NEXTBUTTON_X, FIRSTCHAR_BUTTON_Y + 110, FIRSTCHAR_BUTTON_WIDTH, FIRSTCHAR_BUTTON_WIDTH);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                infoWind = true;
                objectForInfo = characters.get(neededCharacter1);
            }
        }
        batch.draw(characters.get(neededCharacter1).getTexture(), FIRSTCHAR_X, FIRSTCHAR_Y, FIRSTCHAR_WIDTH, FIRSTCHAR_HEIGHT);
        batch.draw(nextCharacterButton, FIRSTCHAR_NEXTBUTTON_X, FIRSTCHAR_BUTTON_Y, FIRSTCHAR_BUTTON_WIDTH, FIRSTCHAR_BUTTON_HEIGHT);
        batch.draw(previousCharacterButton, FIRSTCHAR_BACKBUTTON_X, FIRSTCHAR_BUTTON_Y, FIRSTCHAR_BUTTON_WIDTH, FIRSTCHAR_BUTTON_HEIGHT);
        if (!infoWind && Gdx.input.getX() < FIRSTCHAR_GETXEND && Gdx.input.getX() > FIRSTCHAR_GETXSTART && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < FIRSTCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > FIRSTCHAR_GETYSTART) {
            batch.draw(nextCharacterButton2, FIRSTCHAR_NEXTBUTTON_X, FIRSTCHAR_BUTTON_Y, FIRSTCHAR_BUTTON_WIDTH, FIRSTCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter1++;
                if (neededCharacter1 == 4) {
                    neededCharacter1 = 0;
                }
                batch.draw(characters.get(neededCharacter1).getTexture(), FIRSTCHAR_X, FIRSTCHAR_Y, FIRSTCHAR_WIDTH, FIRSTCHAR_HEIGHT);
            }
        }
        if (!infoWind && Gdx.input.getX() < FIRSTCHAR_GETXEND2 && Gdx.input.getX() > FIRSTCHAR_GETXSTART2 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < FIRSTCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > FIRSTCHAR_GETYSTART) {
            batch.draw(previousCharacterButton2, FIRSTCHAR_BACKBUTTON_X, FIRSTCHAR_BUTTON_Y, FIRSTCHAR_BUTTON_WIDTH, FIRSTCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter1 == 0) {
                    neededCharacter1 = 4;
                }
                neededCharacter1--;
                batch.draw(characters.get(neededCharacter1).getTexture(), FIRSTCHAR_X, FIRSTCHAR_Y, FIRSTCHAR_WIDTH, FIRSTCHAR_HEIGHT);
            }
        } // First character choose ended.

        // Second char choose start:
        batch.draw(infoButton1, SECONDCHAR_NEXTBUTTON_X, SECONDCHAR_BUTTON_Y + 110, SECONDCHAR_BUTTON_WIDTH, SECONDCHAR_BUTTON_WIDTH);
        if (!infoWind && Gdx.input.getX() > SECONDCHAR_NEXTBUTTON_X && Gdx.input.getX() < SECONDCHAR_NEXTBUTTON_X + SECONDCHAR_BUTTON_WIDTH && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - SECONDCHAR_BUTTON_Y - 190 && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - SECONDCHAR_BUTTON_Y - 140) {
            batch.draw(infoButton2, SECONDCHAR_NEXTBUTTON_X, SECONDCHAR_BUTTON_Y + 110, SECONDCHAR_BUTTON_WIDTH, SECONDCHAR_BUTTON_WIDTH);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                infoWind = true;
                objectForInfo = characters.get(neededCharacter2);
            }
        }
        batch.draw(characters.get(neededCharacter2).getTexture(), SECONDCHAR_X, SECONDCHAR_Y, SECONDCHAR_WIDTH, SECONDCHAR_HEIGHT);
        batch.draw(nextCharacterButton, SECONDCHAR_NEXTBUTTON_X, SECONDCHAR_BUTTON_Y, SECONDCHAR_BUTTON_WIDTH, SECONDCHAR_BUTTON_HEIGHT);
        batch.draw(previousCharacterButton, SECONDCHAR_BACKBUTTON_X, SECONDCHAR_BUTTON_Y, SECONDCHAR_BUTTON_WIDTH, SECONDCHAR_BUTTON_HEIGHT);
        if (!infoWind && Gdx.input.getX() < SECONDCHAR_GETXEND && Gdx.input.getX() > SECONDCHAR_GETXSTART && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < SECONDCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > SECONDCHAR_GETYSTART) {
            batch.draw(nextCharacterButton2, SECONDCHAR_NEXTBUTTON_X, SECONDCHAR_BUTTON_Y, SECONDCHAR_BUTTON_WIDTH, SECONDCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter2++;
                if (neededCharacter2 == 4) {
                    neededCharacter2 = 0;
                }
                batch.draw(characters.get(neededCharacter2).getTexture(), SECONDCHAR_X, SECONDCHAR_Y, SECONDCHAR_WIDTH, SECONDCHAR_HEIGHT);
            }
        }
        if (!infoWind && Gdx.input.getX() < SECONDCHAR_GETXEND2 && Gdx.input.getX() > SECONDCHAR_GETXSTART2 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < SECONDCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > SECONDCHAR_GETYSTART) {
            batch.draw(previousCharacterButton2, SECONDCHAR_BACKBUTTON_X, SECONDCHAR_BUTTON_Y, SECONDCHAR_BUTTON_WIDTH, SECONDCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter2 == 0) {
                    neededCharacter2 = 4;
                }
                neededCharacter2--;
                batch.draw(characters.get(neededCharacter2).getTexture(), SECONDCHAR_X, SECONDCHAR_Y, SECONDCHAR_WIDTH, SECONDCHAR_HEIGHT);
            }
        } // Second character choose ended.

        // Third char choose start:
        batch.draw(infoButton1, THIRDCHAR_NEXTBUTTON_X, THIRDCHAR_BUTTON_Y + 110, THIRDCHAR_BUTTON_WIDTH, THIRDCHAR_BUTTON_WIDTH);
        if (!infoWind && Gdx.input.getX() > THIRDCHAR_NEXTBUTTON_X && Gdx.input.getX() < THIRDCHAR_NEXTBUTTON_X + THIRDCHAR_BUTTON_WIDTH && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - THIRDCHAR_BUTTON_Y - 190 && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - THIRDCHAR_BUTTON_Y - 140) {
            batch.draw(infoButton2, THIRDCHAR_NEXTBUTTON_X, THIRDCHAR_BUTTON_Y + 110, THIRDCHAR_BUTTON_WIDTH, THIRDCHAR_BUTTON_WIDTH);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                infoWind = true;
                objectForInfo = characters.get(neededCharacter3);
            }
        }
        batch.draw(characters.get(neededCharacter3).getTexture(), THIRDCHAR_X, THIRDCHAR_Y, THIRDCHAR_WIDTH, THIRDCHAR_HEIGHT);
        batch.draw(nextCharacterButton, THIRDCHAR_NEXTBUTTON_X, THIRDCHAR_BUTTON_Y, THIRDCHAR_BUTTON_WIDTH, THIRDCHAR_BUTTON_HEIGHT);
        batch.draw(previousCharacterButton, THIRDCHAR_BACKBUTTON_X, THIRDCHAR_BUTTON_Y, THIRDCHAR_BUTTON_WIDTH, THIRDCHAR_BUTTON_HEIGHT);
        if (!infoWind && Gdx.input.getX() < THIRDCHAR_GETXEND && Gdx.input.getX() > THIRDCHAR_GETXSTART && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < THIRDCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > THIRDCHAR_GETYSTART) {
            batch.draw(nextCharacterButton2, THIRDCHAR_NEXTBUTTON_X, THIRDCHAR_BUTTON_Y, THIRDCHAR_BUTTON_WIDTH, THIRDCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter3++;
                if (neededCharacter3 == 4) {
                    neededCharacter3 = 0;
                }
                batch.draw(characters.get(neededCharacter3).getTexture(), THIRDCHAR_X, THIRDCHAR_Y, THIRDCHAR_WIDTH, THIRDCHAR_HEIGHT);
            }
        }
        if (!infoWind && Gdx.input.getX() < THIRDCHAR_GETXEND2 && Gdx.input.getX() > THIRDCHAR_GETXSTART2 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < THIRDCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > THIRDCHAR_GETYSTART) {
            batch.draw(previousCharacterButton2, THIRDCHAR_BACKBUTTON_X, THIRDCHAR_BUTTON_Y, THIRDCHAR_BUTTON_WIDTH, THIRDCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter3 == 0) {
                    neededCharacter3 = 4;
                }
                neededCharacter3--;
                batch.draw(characters.get(neededCharacter3).getTexture(), THIRDCHAR_X, THIRDCHAR_Y, THIRDCHAR_WIDTH, THIRDCHAR_HEIGHT);
            }
        } // Third character choose ended.

        // Forth char choose start:
        batch.draw(infoButton1, FORTHCHAR_NEXTBUTTON_X, FORTHCHAR_BUTTON_Y + 110, FORTHCHAR_BUTTON_WIDTH, FORTHCHAR_BUTTON_WIDTH);
        if (!infoWind && Gdx.input.getX() > FORTHCHAR_NEXTBUTTON_X && Gdx.input.getX() < FORTHCHAR_NEXTBUTTON_X + FORTHCHAR_BUTTON_WIDTH && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - FORTHCHAR_BUTTON_Y - 190 && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - FORTHCHAR_BUTTON_Y - 140) {
            batch.draw(infoButton2, FORTHCHAR_NEXTBUTTON_X, FORTHCHAR_BUTTON_Y + 110, FORTHCHAR_BUTTON_WIDTH, FORTHCHAR_BUTTON_WIDTH);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                infoWind = true;
                objectForInfo = characters.get(neededCharacter4);
            }
        }
        batch.draw(characters.get(neededCharacter4).getTexture(), FORTHCHAR_X, FORTHCHAR_Y, FORTHCHAR_WIDTH, FORTHCHAR_HEIGHT);
        batch.draw(nextCharacterButton, FORTHCHAR_NEXTBUTTON_X, FORTHCHAR_BUTTON_Y, FORTHCHAR_BUTTON_WIDTH, FORTHCHAR_BUTTON_HEIGHT);
        batch.draw(previousCharacterButton, FORTHCHAR_BACKBUTTON_X, FORTHCHAR_BUTTON_Y, FORTHCHAR_BUTTON_WIDTH, FORTHCHAR_BUTTON_HEIGHT);
        if (!infoWind && Gdx.input.getX() < FORTHCHAR_GETXEND && Gdx.input.getX() > FORTHCHAR_GETXSTART && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < FORTHCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > FORTHCHAR_GETYSTART) {
            batch.draw(nextCharacterButton2, FORTHCHAR_NEXTBUTTON_X, FORTHCHAR_BUTTON_Y, FORTHCHAR_BUTTON_WIDTH, FORTHCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                neededCharacter4++;
                if (neededCharacter4 == 4) {
                    neededCharacter4 = 0;
                }
                batch.draw(characters.get(neededCharacter4).getTexture(), FORTHCHAR_X, FORTHCHAR_Y, FORTHCHAR_WIDTH, FORTHCHAR_HEIGHT);
            }
        }
        if (!infoWind && Gdx.input.getX() < FORTHCHAR_GETXEND2 && Gdx.input.getX() > FORTHCHAR_GETXSTART2 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < FORTHCHAR_GETYEND && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > FORTHCHAR_GETYSTART) {
            batch.draw(previousCharacterButton2, FORTHCHAR_BACKBUTTON_X, FORTHCHAR_BUTTON_Y, FORTHCHAR_BUTTON_WIDTH, FORTHCHAR_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (neededCharacter4 == 0) {
                    neededCharacter4 = 4;
                }
                neededCharacter4--;
                batch.draw(characters.get(neededCharacter4).getTexture(), FORTHCHAR_X, FORTHCHAR_Y, FORTHCHAR_WIDTH, FORTHCHAR_HEIGHT);
            }
        } // Forth character choose ended.

        //Game start button:
        batch.draw(startButton, PLAY_BUTTON_X_START, PLAYBUTTON_Y_FORBUTTONCHANGE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        if (!infoWind && Gdx.input.getX() > PLAY_BUTTON_X_START && Gdx.input.getX() < PLAY_BUTTON_X_END && Gdx.input.getY() > PLAY_BUTTON_Y_START && Gdx.input.getY() < PLAY_BUTTON_Y_END) {
            batch.draw(startButton2, PLAY_BUTTON_X_START, PLAYBUTTON_Y_FORBUTTONCHANGE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (singleGame && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && neededBadCharacter1 != -101) {
                badCharacters = Arrays.asList(new BadCharacterCreating().createCharacter(rightBadCharacters, neededBadCharacter1, badCharacter1), new BadCharacterCreating().createCharacter(rightBadCharacters, neededBadCharacter2, badCharacter2), new BadCharacterCreating().createCharacter(rightBadCharacters, neededBadCharacter3, badCharacter3), new BadCharacterCreating().createCharacter(rightBadCharacters, neededBadCharacter4, badCharacter4));
                List<GameObject> rightCharactersList = new ArrayList<>(Arrays.asList(new CharacterCreating().createCharacter(characters, neededCharacter1, goodCharacter1, 1), new CharacterCreating().createCharacter(characters, neededCharacter2, goodCharacter2, 2), new CharacterCreating().createCharacter(characters, neededCharacter3, goodCharacter3, 3), new CharacterCreating().createCharacter(characters, neededCharacter4, goodCharacter4, 4)));
                game.setScreen(new GameScreen(rightCharactersList, badCharacters, game, music, openLevelNumber, lvlPlaying));
            } else if (!singleGame && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                List<GameObject> rightCharactersList = new ArrayList<>(Arrays.asList(new CharacterCreating().createCharacter(characters, neededCharacter1, goodCharacter1, 1), new CharacterCreating().createCharacter(characters, neededCharacter2, goodCharacter2, 2), new CharacterCreating().createCharacter(characters, neededCharacter3, goodCharacter3, 3), new CharacterCreating().createCharacter(characters, neededCharacter4, goodCharacter4, 4)));
                game.setScreen(new MultiplayerScreen(rightCharactersList));
            }
        }

        //back button:
        batch.draw(backButton, BACK_BUTTON_X_START, BACKBUTTON_Y_FORBUTTONCHANGE, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        if (!infoWind && Gdx.input.getX() > BACK_BUTTON_X_START && Gdx.input.getX() < BACK_BUTTON_X_END && Gdx.input.getY() > BACK_BUTTON_Y_START && Gdx.input.getY() < BACK_BUTTON_Y_END) {
            batch.draw(backButton2, BACK_BUTTON_X_START, BACKBUTTON_Y_FORBUTTONCHANGE, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new MainMenuScreen(game, openLevelNumber, music, true));
            }
        }

        //info screen:
        if (infoWind) {
            batch.draw(infoWindow, INFOWINDOW_START_X, INFOWINDOW_START_Y, INFOWINDOW_WIDTH, INFOWINDOW_HEIGHT);
            batch.draw(closeButton1, INFOWINDOW_START_X + INFOWINDOW_WIDTH - 100, INFOWINDOW_START_Y + INFOWINDOW_HEIGHT - 100, 60, 60);
            batch.draw(objectForInfo.getTexture(), INFOWINDOW_START_X + 60, INFOWINDOW_START_Y + INFOWINDOW_HEIGHT - FORTHCHAR_HEIGHT - 60, FORTHCHAR_WIDTH, FORTHCHAR_HEIGHT);
            Color fontColor = new Color(Color.rgb888(0f, 0f, 85f));
            font.setColor(new Color(fontColor));
            font.getData().setScale(2);
            font.draw(batch, objectForInfo.getName(), INFOWINDOW_START_X + 60, INFOWINDOW_START_Y + INFOWINDOW_HEIGHT - FORTHCHAR_HEIGHT - 100);
            fontColor = new Color(Color.rgb888(0f, 0f, 102f));
            font.setColor(new Color(fontColor));
            font.getData().setScale(1.7f);
            font.draw(batch, "Health: " + objectForInfo.getHealth(), INFOWINDOW_START_X + 60, INFOWINDOW_START_Y + INFOWINDOW_HEIGHT - FORTHCHAR_HEIGHT - 135);
            font.draw(batch, "Power: " + objectForInfo.getPower(), INFOWINDOW_START_X + 60, INFOWINDOW_START_Y + INFOWINDOW_HEIGHT - FORTHCHAR_HEIGHT - 165);
            font.draw(batch, "Mana: " + objectForInfo.getMana(), INFOWINDOW_START_X + 60, INFOWINDOW_START_Y + INFOWINDOW_HEIGHT - FORTHCHAR_HEIGHT - 195);
            if (Gdx.input.getX() > INFOWINDOW_START_X + INFOWINDOW_WIDTH - 100 && Gdx.input.getX() < INFOWINDOW_START_X + INFOWINDOW_WIDTH - 40 && Gdx.input.getY() > INFOWINDOW_START_Y && Gdx.input.getY() < INFOWINDOW_START_Y + 60) {
                batch.draw(closeButton2, INFOWINDOW_START_X + INFOWINDOW_WIDTH - 100, INFOWINDOW_START_Y + INFOWINDOW_HEIGHT - 100, 60, 60);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    infoWind = false;
                }
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
